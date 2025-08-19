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

            # === PostgreSQL ===
            - name: SPRING_DATASOURCE_URL
              value: "jdbc:postgresql://{{ .Values.postgres.host }}:{{ .Values.postgres.port }}/{{ .Values.postgres.database }}"
            - name: SPRING_DATASOURCE_USERNAME
              value: {{ .Values.postgres.username | quote }}
            - name: SPRING_DATASOURCE_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: {{ .Values.postgres.passwordSecret.name }}
                  key: {{ .Values.postgres.passwordSecret.key }}

            # === Redis ===
            {{- if .Values.redis.enabled }}
            - name: SPRING_DATA_REDIS_HOST
              value: {{ .Values.redis.host | quote }}
            - name: SPRING_DATA_REDIS_PORT
              value: {{ .Values.redis.port | quote }}
            - name: SPRING_DATA_REDIS_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: {{ .Values.postgres.passwordSecret.name }}
                  key: {{ .Values.postgres.passwordSecret.key }}
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