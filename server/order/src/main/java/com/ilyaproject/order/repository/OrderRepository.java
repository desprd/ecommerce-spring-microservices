package com.ilyaproject.order.repository;

import com.ilyaproject.order.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = """
        SELECT * FROM orders o
        WHERE o.course_id = :courseId
          AND o.customer_id = :customerId
          AND o.status IN ('PAID', 'PENDING')
        LIMIT 1
        """, nativeQuery = true)
    Optional<Order> findSameOrder(@Param("customerId") Long customerId, @Param("courseId") Long courseId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE orders
        SET status = 'PAID'
        WHERE order_id = :orderId
        """, nativeQuery = true)
    int changeOrderStatusToPaid(@Param("orderId") Long orderId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE orders
        SET status = 'CANCELLED'
        WHERE order_id = :orderId
            AND status = 'PENDING'
        """, nativeQuery = true)
    int changeOrderStatusToCancelled(@Param("orderId") Long orderId);
}
