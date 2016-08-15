package com.tomkasp.fitnow.shop.application.service;

import com.tomkasp.fitnow.infrastructure.factory.DtoFactory;
import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.readmodel.PaymentDetailsDto;

public class PaymentDetailsFactory implements DtoFactory<PaymentDetailsDto, PaymentDetails> {


    @Override
    public PaymentDetails toEntity(PaymentDetailsDto paymentDetailsDto) {
        return null;
    }

    @Override
    public PaymentDetailsDto toDto(PaymentDetails paymentDetails) {
        return null;
    }
}
