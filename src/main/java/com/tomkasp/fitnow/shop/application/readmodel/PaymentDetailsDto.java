package com.tomkasp.fitnow.shop.application.readmodel;

public class PaymentDetailsDto {

    private String signature;
    private String firstName;
    private String lastName;
    private String email;
    private String clientIp;
    private String datetime;
    private String description;
    private String amount;
    private String sessionId;

    public String getSignature() {
        return signature;
    }

    public PaymentDetailsDto setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PaymentDetailsDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PaymentDetailsDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PaymentDetailsDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public PaymentDetailsDto setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public String getDatetime() {
        return datetime;
    }

    public PaymentDetailsDto setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PaymentDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public PaymentDetailsDto setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public PaymentDetailsDto setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }
}
