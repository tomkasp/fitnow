package com.tomkasp.fitnow.shop.dto;

import com.tomkasp.fitnow.shop.application.paymentproviders.OrderType;

import java.io.Serializable;
import java.math.BigDecimal;


public class CreateOrderDto implements Serializable{

    private OrderType orderType;
    private String paymentIntegrationId;
    private BigDecimal amount;


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
