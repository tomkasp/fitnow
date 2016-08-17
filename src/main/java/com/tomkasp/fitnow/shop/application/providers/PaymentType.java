package com.tomkasp.fitnow.shop.application.providers;

public enum PaymentType {


    STANDARD("standard"),
    SUPER("super"),
    PREMIUM("premium");

    private final String type;

    PaymentType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
