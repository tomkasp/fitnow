package com.tomkasp.fitnow.shop.application.readmodel.impl;


import com.tomkasp.fitnow.shop.application.domain.OrderDetails;
import com.tomkasp.fitnow.shop.application.providers.OrderType;
import com.tomkasp.fitnow.shop.application.readmodel.OrderDetailsFinder;
import com.tomkasp.fitnow.shop.application.service.FetchOrderService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class OrderDetailsFinderImpl implements OrderDetailsFinder {

    @Inject
    FetchOrderService fetchOrderService;


    @Override
    public OrderDetails getOrderDetails(OrderType orderType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return fetchOrderService.fetchOrder(orderType);
        //todo combain order detail with payment payments from database and attaching current payment satuts for the user
    }


}
