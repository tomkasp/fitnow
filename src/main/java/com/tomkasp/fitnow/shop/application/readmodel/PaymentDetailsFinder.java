package com.tomkasp.fitnow.shop.application.readmodel;


import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.providers.PaymentType;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface PaymentDetailsFinder {

    PaymentDetails getPaymentDetails(PaymentType paymentType) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
