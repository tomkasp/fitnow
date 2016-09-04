package com.tomkasp.fitnow.shop.application.paymentproviders.payu;

public class PayUSearchCriteria {

    final private String posId;
    final private String sessionId;
    final private String ts;
    final private String signature;

    public PayUSearchCriteria(String posId, String sessionId, String ts, String signature){
        this.posId = posId;
        this.sessionId = sessionId;
        this.ts = ts;
        this.signature = signature;
    }

    public String getPosId() {
        return posId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getTs() {
        return ts;
    }

    public String getSignature() {
        return signature;
    }
}
