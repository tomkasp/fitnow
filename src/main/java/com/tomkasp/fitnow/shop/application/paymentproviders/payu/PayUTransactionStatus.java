package com.tomkasp.fitnow.shop.application.paymentproviders.payu;

public enum PayUTransactionStatus {

    NEW("1"),
    CANCELED("2"),
    REJECTED("3"),
    INITIALIZED("4"),
    WAITING("5"),
    REFUND("7"),
    PAID("99"),
    UNKNOWN("888");

    private String payUPaymentStatus;

    PayUTransactionStatus(String status) {
        this.payUPaymentStatus = status;
    }

    public static PayUTransactionStatus findByString(String stringValue){
        for(PayUTransactionStatus v : values()){
            if( v.toString().equals(stringValue)){
                return v;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String toString() {
        return this.payUPaymentStatus;
    }
}
