package com.tomkasp.fitnow.shop.application.providers;

import java.math.BigDecimal;

import static com.tomkasp.fitnow.shop.application.providers.PaymentType.*;
import static com.tomkasp.fitnow.shop.application.providers.PaymentType.PREMIUM;

public class PriceProvider {


    public static BigDecimal getPrice(PaymentType paymentType) {
        if (PREMIUM.equals(paymentType)) {
            return new BigDecimal(15000);
        } else if (STANDARD.equals(paymentType)) {
            return new BigDecimal(5900);
        } else if (SUPER.equals(paymentType)) {
            return new BigDecimal(29000);
        }
        return BigDecimal.ZERO;
    }
}
