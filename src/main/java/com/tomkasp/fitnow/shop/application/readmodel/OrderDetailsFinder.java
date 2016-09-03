package com.tomkasp.fitnow.shop.application.readmodel;


import com.tomkasp.fitnow.shop.application.domain.OrderDetails;
import com.tomkasp.fitnow.shop.application.providers.OrderType;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface OrderDetailsFinder {

    OrderDetails getOrderDetails(OrderType orderType) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
