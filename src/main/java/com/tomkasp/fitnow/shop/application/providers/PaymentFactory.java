package com.tomkasp.fitnow.shop.application.providers;

import com.tomkasp.fitnow.shop.application.providers.payu.PayUProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactory {

    private final Logger log = LoggerFactory.getLogger(PaymentFactory.class);

    private PaymentProvider paymentProvider = null;

    @Value("${fitnow.payments.provider}")
    String provider;

    public PaymentProvider build() {
        log.debug("USED payment provider: {}", provider);
        if (PaymentProvidersEnum.PAYU.toString().equalsIgnoreCase(provider)) {
            if (paymentProvider == null) {
                paymentProvider = new PayUProvider();
            } else {
                return paymentProvider;
            }
        }
        return paymentProvider;
    }
}
