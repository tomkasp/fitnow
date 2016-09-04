package com.tomkasp.fitnow.shop.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.shop.application.paymentproviders.OrderType;
import com.tomkasp.fitnow.shop.domain.OrderDetails;
import com.tomkasp.fitnow.shop.readmodel.OrderDetailsFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/shop/orders/")
public class OrderTypesResource {

    private final Logger log = LoggerFactory.getLogger(OrderTypesResource.class);

    private final OrderDetailsFinder orderDetailsFinder;

    @Autowired
    public OrderTypesResource(OrderDetailsFinder orderDetailsFinder) {
        this.orderDetailsFinder = orderDetailsFinder;
    }

    @RequestMapping(value = "{orderType}", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<OrderDetails> getPaymentTypeDetails(@PathVariable String orderType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        OrderType type = OrderType.valueOf(orderType.toUpperCase());
        final OrderDetails orderDetails = orderDetailsFinder.getOrderDetails(type);
        log.debug("Payment details value: {}", orderDetails);
        return ResponseEntity.ok(orderDetails);
    }
}
