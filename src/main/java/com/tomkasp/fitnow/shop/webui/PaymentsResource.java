package com.tomkasp.fitnow.shop.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.cqrs.command.Gate;
import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.readmodel.PaymentDetailsFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/payments")
public class PaymentsResource {

    private final Logger log = LoggerFactory.getLogger(PaymentsResource.class);

    @Inject
    private PaymentDetailsFinder paymentDetailsFinder;

    @Inject
    private Gate gate;


    @RequestMapping(method = RequestMethod.GET)
    @Timed
    public ResponseEntity<PaymentDetails> getPaymentSignature() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final PaymentDetails paymentDetails = paymentDetailsFinder.getPaymentDetails();
        log.debug("Payment details value: {}", paymentDetails);
//        gate.dispatch(new PaymentsCommand());
        return ResponseEntity.ok(paymentDetails);
    }
}
