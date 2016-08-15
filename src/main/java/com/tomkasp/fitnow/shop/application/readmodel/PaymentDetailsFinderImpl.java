package com.tomkasp.fitnow.shop.application.readmodel;


import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.service.FetchPaymentService;
import com.tomkasp.fitnow.shop.application.service.PaymentDetailsFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class PaymentDetailsFinderImpl implements PaymentDetailsFinder {

    @Inject
    FetchPaymentService fetchPaymentService;


    @Override
    public PaymentDetails getPaymentDetails() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return fetchPaymentService.fetchPayment();
        //todo add retriving payments from database and attaching current payment satuts for the user
    }


}
