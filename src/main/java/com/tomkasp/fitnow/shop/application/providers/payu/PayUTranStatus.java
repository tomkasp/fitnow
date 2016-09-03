package com.tomkasp.fitnow.shop.application.providers.payu;

public enum PayUTranStatus {

    NEW("1"),
    CANCELED("2"),
    REJECTED("3"),
    INITIALIZED("4"),
    WAITING("5"),
    REFUND("7"),
    PAID("99"),
    UNKNOWN("888");

    private String payUPaymentStatus;

    PayUTranStatus(String status) {
        this.payUPaymentStatus = status;
    }

    public String getPayUPaymentStatus() {
        return payUPaymentStatus;
    }
}
