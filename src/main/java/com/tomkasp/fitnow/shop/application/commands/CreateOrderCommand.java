package com.tomkasp.fitnow.shop.application.commands;


import com.tomkasp.fitnow.shop.application.paymentproviders.OrderType;

import java.math.BigDecimal;

public class CreateOrderCommand {

    final private OrderType orderType;
    final private String paymentIntegrationId;
    final private BigDecimal amount;

    public CreateOrderCommand(OrderType orderType, String paymentIntegrationId, BigDecimal amount) {
        this.orderType = orderType;
        this.paymentIntegrationId = paymentIntegrationId;
        this.amount = amount;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public String getPaymentIntegrationId() {
        return paymentIntegrationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
