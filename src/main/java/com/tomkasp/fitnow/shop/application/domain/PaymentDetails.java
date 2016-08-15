package com.tomkasp.fitnow.shop.application.domain;

public class PaymentDetails {

    private final String name;
    private final String surname;
    private final String email;
    private final String sessionId;
    private final String amount;
    private final String description;
    private final String clientIp;
    private final String timeStamp;
    private String paymentSignature;

    public PaymentDetails(String name, String surname, String email, String sessionId, String amount, String description, String clientIp, String timeStamp) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sessionId = sessionId;
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

    public String getSessionId() {
        return sessionId;
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

    public String getPaymentSignature() {
        return paymentSignature;
    }

    public PaymentDetails setPaymentSignature(String paymentSignature) {
        this.paymentSignature = paymentSignature;
        return this;
    }
}
