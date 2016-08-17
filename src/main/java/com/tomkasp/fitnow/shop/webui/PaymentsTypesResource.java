package com.tomkasp.fitnow.shop.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.providers.PaymentType;
import com.tomkasp.fitnow.shop.application.readmodel.PaymentDetailsFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/payments/types")
public class PaymentsTypesResource {

    private final Logger log = LoggerFactory.getLogger(PaymentsResource.class);

    @Inject
    private PaymentDetailsFinder paymentDetailsFinder;

    @RequestMapping(value = "{paymentType}", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<PaymentDetails> getPaymentSignature(@PathVariable String paymentType) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        PaymentType type = PaymentType.valueOf(paymentType.toUpperCase());
        final PaymentDetails paymentDetails = paymentDetailsFinder.getPaymentDetails(type);
        log.debug("Payment details value: {}", paymentDetails);
        return ResponseEntity.ok(paymentDetails);
    }
}
