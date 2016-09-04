package com.tomkasp.fitnow.shop.application.service;

import com.tomkasp.domain.User;
import com.tomkasp.fitnow.shop.domain.OrderDetails;
import com.tomkasp.fitnow.shop.application.paymentproviders.PaymentFactory;
import com.tomkasp.fitnow.shop.application.paymentproviders.PaymentProvider;
import com.tomkasp.fitnow.shop.application.paymentproviders.OrderType;
import com.tomkasp.fitnow.shop.application.paymentproviders.PriceProvider;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class FetchOrderService {

    private final Logger log = LoggerFactory.getLogger(FetchOrderService.class);

    private final UserService userService;

    private final PaymentFactory paymentFactory;

    @Autowired
    public FetchOrderService(UserService userService, PaymentFactory paymentFactory) {
        this.userService = userService;
        this.paymentFactory = paymentFactory;
    }

    public OrderDetails fetchOrder(OrderType orderType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User userWithAuthorities = userService.getUserWithAuthorities();
        final String firstName = getFirstName();
        final String lastName = getLastName();
        final String email = userWithAuthorities.getEmail();
        final String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        final String amount = PriceProvider.getPrice(orderType).toString();
        final String ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest().getRemoteAddr();
        final String currentDate = Long.toString(System.currentTimeMillis());
        PaymentProvider paymentProvider = paymentFactory.build();

        OrderDetails orderDetails = new OrderDetails(firstName, lastName, email, sessionId, amount, orderType.name().toString(), ip, currentDate);
        final String paymentSignature = paymentProvider.createPaymentSignature(orderDetails);
        orderDetails.setOrderSignature(paymentSignature);
        return orderDetails;
    }

    private String getFirstName() {
        String firstName;
        User userWithAuthorities = userService.getUserWithAuthorities();
        firstName = Optional.ofNullable(userWithAuthorities.getFirstName())
            .map(result -> result)
            .orElse(userWithAuthorities.getLogin());
        return firstName;
    }

    private String getLastName() {
        String lastName;
        User userWithAuthorities = userService.getUserWithAuthorities();
        lastName = Optional.ofNullable(userWithAuthorities.getLastName())
            .map(result -> result)
            .orElse(userWithAuthorities.getLogin());
        return lastName;
    }
}
