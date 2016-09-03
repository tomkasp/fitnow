package com.tomkasp.fitnow.shop.application.domain;


import org.joda.time.DateTime;

import java.math.BigDecimal;

public class Payment {

    BigDecimal amount;
    String orderId;
    PaymentStatus paymentStatus;
    DateTime timeStamp;

    OrderDetails orderDetails;

}
