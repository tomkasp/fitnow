package com.tomkasp.fitnow.shop.application.service;

import com.tomkasp.fitnow.infrastructure.factory.DtoFactory;
import com.tomkasp.fitnow.shop.domain.OrderDetails;
import com.tomkasp.fitnow.shop.readmodel.PaymentDetailsDto;

public class PaymentDetailsFactory implements DtoFactory<PaymentDetailsDto, OrderDetails> {


    @Override
    public OrderDetails toEntity(PaymentDetailsDto paymentDetailsDto) {
        return null;
    }

    @Override
    public PaymentDetailsDto toDto(OrderDetails orderDetails) {
        return null;
    }
}
