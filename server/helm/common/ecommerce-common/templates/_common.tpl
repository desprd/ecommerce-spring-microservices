{{- define "common.deployment" -}}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ .Values.deploymentName }}
  labels: { app: {{ .Values.appLabel }} }
spec:
  replicas: {{ .Values.replicaCount }}
  selector:
    matchLabels: { app: {{ .Values.appLabel }} }
  template:
    metadata:
      labels: { app: {{ .Values.appLabel }} }
    spec:
      containers:
        - name: {{ .Values.appLabel }}
          image: "{{ .Values.image.repository }}:{{ .Values.image.tag }}"
          imagePullPolicy: {{ .Values.image.pullPolicy | default "IfNotPresent" }}
          ports:
            - name: http
              containerPort: {{ .Values.containerPort }}
              protocol: TCP
            {{- if .Values.grpc.enabled }}
            - name: grpc
              containerPort: {{ .Values.grpc.port }}
              protocol: TCP
            {{- end }}
          env:

            # === Profile ===
            {{- if .Values.profile.enabled }}
            - name: SPRING_PROFILE_ACTIVE
              value: {{ .Values.profile.active | quote }}
            {{- end }}

            # === PostgreSQL ===
            {{- if .Values.postgres.enabled }}
            - name: SPRING_DATASOURCE_URL
              value: "jdbc:postgresql://{{ .Values.postgres.host }}:{{ .Values.postgres.port }}/{{ .Values.postgres.database }}"
            - name: SPRING_DATASOURCE_USERNAME
              value: {{ .Values.postgres.username | quote }}
            - name: SPRING_DATASOURCE_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: {{ .Values.postgres.passwordSecret.name }}
                  key: {{ .Values.postgres.passwordSecret.key }}
            {{- end }}

            # === Redis ===
            {{- if .Values.redis.enabled }}
            - name: SPRING_DATA_REDIS_HOST
              value: {{ .Values.redis.host | quote }}
            - name: SPRING_DATA_REDIS_PORT
              value: {{ .Values.redis.port | quote }}
            - name: SPRING_DATA_REDIS_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: {{ .Values.redis.passwordSecret.name }}
                  key: {{ .Values.redis.passwordSecret.key }}
            {{- end }}

            # === gRPC ===
            {{- if .Values.grpc.enabled }}
            - name: GRPC_SERVER_PORT
              value: {{ .Values.grpc.port | quote }}
            {{- end }}
            {{- if .Values.catalogGrpc.enabled }}
            - name: CATALOG_GRPC_ADDR
              value: "dns:///{{ .Values.catalogGrpc.host }}:{{ .Values.catalogGrpc.port }}"
            - name: CATALOG_GRPC_NEGOTIATION
              value: "PLAINTEXT"
            {{- end }}
            {{- if .Values.orderGrpc.enabled }}
            - name: ORDER_GRPC_ADDR
              value: "dns:///{{ .Values.orderGrpc.host }}:{{ .Values.orderGrpc.port }}"
            - name: ORDER_GRPC_NEGOTIATION
              value: "PLAINTEXT"
            {{- end }}
            # === Kafka ===
            {{- if .Values.kafka.enabled }}
            - name: BOOTSTRAP_SERVERS_KAFKA
              value: {{ .Values.kafka.server }}
            {{- end }}
            {{- if .Values.kafkaClient.enabled }}
            - name: GROUP_ID_KAFKA
              value: {{ .Values.kafkaClient.group }}
            {{- if .Values.kafkaClient.topic.payment.enabled }}
            - name: TOPIC_PAYMENT_SUCCEEDED
              value: {{ .Values.kafkaClient.topic.payment.paymentSucceeded }}
            - name: TOPIC_PAYMENT_FAILED
              value: {{ .Values.kafkaClient.topic.payment.paymentFailed }}
            {{- end }}
            {{- if .Values.kafkaClient.topic.message.enabled }}
            - name: TOPIC_MESSAGE_SUCCESS
              value: {{ .Values.kafkaClient.topic.message.successMessage }}
            {{- end }}
            - name: KAFKA_TRUSTED_PACKAGE
              value: {{ .Values.kafkaClient.trusted }}
            {{- end }}

            # === Email ===
            {{- if .Values.email.enabled }}
            - name: SPRING_MAIL_HOST
              value: {{ .Values.email.host }}
            - name: SPRING_MAIL_PORT
              value: {{ .Values.email.port | quote }}
            - name: SPRING_MAIL_USERNAME
              valueFrom:
                secretKeyRef:
                  name: {{ .Values.email.secret.name }}
                  key: {{ .Values.email.secret.usernameKey }}
            - name: SPRING_MAIL_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: {{ .Values.email.secret.name }}
                  key: {{ .Values.email.secret.passwordKey }}
            - name: APP_MAIL_SENDER
              value: {{ .Values.email.sender }}
            - name: APP_MAIL_RECIPIENT
              value: {{ .Values.email.recipient }}
            {{- end }}

            # === Oauth2 ===
            {{- if .Values.oauth2.enabled }}
            - name: OAUTH2_JWT_URI
              value: {{ .Values.oauth2.uri | quote }}
            {{- end }}

          # Probes: choose gRPC or HTTP actuator
          {{- if and .Values.grpc.enabled .Values.probes.grpc.enabled }}
          livenessProbe:
            exec: { command: [ "{{ .Values.probes.grpc.path }}", "-addr=:{{ .Values.grpc.port }}" ] }
            initialDelaySeconds: 5
            periodSeconds: 10
          readinessProbe:
            exec: { command: [ "{{ .Values.probes.grpc.path }}", "-addr=:{{ .Values.grpc.port }}" ] }
            initialDelaySeconds: 5
            periodSeconds: 5
          {{- else if .Values.probes.http.enabled }}
          livenessProbe:
            httpGet: { path: {{ .Values.probes.http.path }}, port: {{ .Values.containerPort }} }
            initialDelaySeconds: 5
            periodSeconds: 10
          readinessProbe:
            httpGet: { path: {{ .Values.probes.http.path }}, port: {{ .Values.containerPort }} }
            initialDelaySeconds: 5
            periodSeconds: 5
          {{- end }}

          resources:
            {{- toYaml .Values.resources | nindent 12 }}
{{- end -}}

{{- define "common.service" -}}
apiVersion: v1
kind: Service
metadata:
  name: {{ .Values.serviceName }}
spec:
  selector: { app: {{ .Values.appLabel }} }
  type: {{ .Values.service.type }}
  ports:
    - name: http
      port: {{ .Values.service.port }}
      targetPort: {{ .Values.service.targetPort }}
      protocol: TCP
      appProtocol: http
    {{- if .Values.grpc.enabled }}
    - name: grpc
      port: {{ .Values.grpc.port }}
      targetPort: {{ .Values.grpc.port }}
      protocol: TCP
      appProtocol: grpc
    {{- end }}
{{- end -}}