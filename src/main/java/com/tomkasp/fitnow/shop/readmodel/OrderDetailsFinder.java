package com.tomkasp.fitnow.shop.readmodel;


import com.tomkasp.fitnow.shop.application.paymentproviders.OrderType;
import com.tomkasp.fitnow.shop.domain.OrderDetails;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface OrderDetailsFinder {

    OrderDetails getOrderDetails(OrderType orderType) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
