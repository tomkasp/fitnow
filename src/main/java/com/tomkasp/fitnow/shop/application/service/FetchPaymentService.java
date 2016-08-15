package com.tomkasp.fitnow.shop.application.service;

import com.tomkasp.domain.User;
import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.providers.PaymentFactory;
import com.tomkasp.fitnow.shop.application.providers.PaymentProvider;
import com.tomkasp.fitnow.shop.application.providers.PriceProvider;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class FetchPaymentService {

    private final Logger log = LoggerFactory.getLogger(FetchPaymentService.class);

    @Inject
    UserService userService;

    @Inject
    PaymentFactory paymentFactory;

    public PaymentDetails fetchPayment() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User userWithAuthorities = userService.getUserWithAuthorities();
        final String firstName = getFirstName();
        final String lastName = getLastName();
        final String email = userWithAuthorities.getEmail();
        final String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        final String amount = PriceProvider.getPrice().toString();
        final String ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest().getRemoteAddr();
        final String currentDate = Long.toString(System.currentTimeMillis());
        PaymentProvider paymentProvider = paymentFactory.build();
        final String description = "description";

        PaymentDetails paymentDetails = new PaymentDetails(firstName, lastName, email, sessionId, amount, description, ip, currentDate);
        final String paymentSignature = paymentProvider.createPaymentSignature(paymentDetails);
        paymentDetails.setPaymentSignature(paymentSignature);
        return paymentDetails;
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
