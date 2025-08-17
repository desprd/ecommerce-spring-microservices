package com.ilyaproject.order.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long courseId;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;
}
