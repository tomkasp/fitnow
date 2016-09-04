package com.tomkasp.fitnow.shop.infrastructure;

import com.tomkasp.fitnow.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
