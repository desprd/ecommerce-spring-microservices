package com.ilyaproject.order.repository;

import com.ilyaproject.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query(value = """
            SELECT * FROM orders o
            WHERE o.course_id = :courseId
                AND o.customer_id = :customerId
                AND o.price = :price
                AND o.status IN ('PENDING')
            LIMIT 1
            """, nativeQuery = true)
    Optional<Order> findOrderByPaymentInformation(@Param("courseId") Long courseId, @Param("customerId") Long customerId, @Param("price") BigDecimal price);
}
