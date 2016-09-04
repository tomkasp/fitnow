package com.tomkasp.fitnow.shop.domain;

public class OrderDetails {

    private final String name;
    private final String surname;
    private final String email;
    private final String orderId;
    private final String amount;
    private final String description;
    private final String clientIp;
    private final String timeStamp;
    private String orderSignature;

    public OrderDetails(String name, String surname, String email, String orderId, String amount, String description, String clientIp, String timeStamp) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.orderId = orderId;
        this.amount = amount;
        this.description = description;
        this.clientIp = clientIp;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getOrderSignature() {
        return orderSignature;
    }

    public OrderDetails setOrderSignature(String orderSignature) {
        this.orderSignature = orderSignature;
        return this;
    }
}
