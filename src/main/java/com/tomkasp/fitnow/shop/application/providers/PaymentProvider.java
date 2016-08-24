package com.tomkasp.fitnow.shop.application.providers;

import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface PaymentProvider {


    String createPaymentSignature(PaymentDetails paymentDetails) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    String getPaymentStatus(String paymentIdentifier);

}
