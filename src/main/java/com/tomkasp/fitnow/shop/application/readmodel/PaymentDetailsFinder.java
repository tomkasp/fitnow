package com.tomkasp.fitnow.shop.application.readmodel;


import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface PaymentDetailsFinder {
    PaymentDetails getPaymentDetails() throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
