package com.tomkasp.fitnow.shop.infrastructure;

import com.tomkasp.fitnow.shop.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByIntegrationId(String integrationId);
}
