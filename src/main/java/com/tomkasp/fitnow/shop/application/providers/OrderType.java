package com.tomkasp.fitnow.shop.application.providers;

public enum OrderType {


    STANDARD("standard"),
    SUPER("super"),
    PREMIUM("premium");

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
