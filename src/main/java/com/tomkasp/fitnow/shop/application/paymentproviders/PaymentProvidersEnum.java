package com.tomkasp.fitnow.shop.application.paymentproviders;

public enum PaymentProvidersEnum {

    PAYU("PAYU");

    private final String providerName;

    /**
     * @param providerName
     */
    PaymentProvidersEnum(final String providerName) {
        this.providerName = providerName;
    }

    @Override
    public String toString() {
        return providerName;
    }
}
