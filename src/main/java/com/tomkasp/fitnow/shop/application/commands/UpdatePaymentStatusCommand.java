package com.tomkasp.fitnow.shop.application.commands;

import com.tomkasp.fitnow.cqrs.annotations.Command;
import com.tomkasp.fitnow.shop.domain.PaymentStatus;

import java.io.Serializable;

@Command
public class UpdatePaymentStatusCommand implements Serializable {


    private final PaymentStatus newPaymentStatus;
    private final String paymentIntegrationId;

    public UpdatePaymentStatusCommand(PaymentStatus paymentStatus, String sessionId) {
        this.newPaymentStatus = paymentStatus;
        this.paymentIntegrationId = sessionId;
    }

    public PaymentStatus getNewPaymentStatus() {
        return newPaymentStatus;
    }

    public String getPaymentIntegrationId() {
        return paymentIntegrationId;
    }
}
