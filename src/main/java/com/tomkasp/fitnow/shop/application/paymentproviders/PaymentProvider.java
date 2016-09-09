package com.tomkasp.fitnow.shop.application.paymentproviders;

import com.tomkasp.fitnow.shop.domain.OrderDetails;
import com.tomkasp.fitnow.shop.domain.PaymentStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface PaymentProvider<T> {


    String createPaymentSignature(OrderDetails orderDetails) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    PaymentStatus retrivePaymentStatus(T paymentIdentifier) throws IOException;

}
