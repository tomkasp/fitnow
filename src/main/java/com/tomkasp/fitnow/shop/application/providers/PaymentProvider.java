package com.tomkasp.fitnow.shop.application.providers;

import com.tomkasp.fitnow.shop.application.domain.OrderDetails;
import com.tomkasp.fitnow.shop.application.domain.PaymentStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface PaymentProvider<T> {


    String createPaymentSignature(OrderDetails orderDetails) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    PaymentStatus getPaymentStatus(T paymentIdentifier) throws IOException;

}
