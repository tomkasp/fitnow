package com.tomkasp.fitnow.shop.application.paymentproviders;

import java.math.BigDecimal;

import static com.tomkasp.fitnow.shop.application.paymentproviders.OrderType.*;
import static com.tomkasp.fitnow.shop.application.paymentproviders.OrderType.PREMIUM;

public class PriceProvider {


    public static BigDecimal getPrice(OrderType orderType) {
        if (PREMIUM.equals(orderType)) {
            return new BigDecimal(15000);
        } else if (STANDARD.equals(orderType)) {
            return new BigDecimal(5900);
        } else if (SUPER.equals(orderType)) {
            return new BigDecimal(29000);
        }
        return BigDecimal.ZERO;
    }
}
