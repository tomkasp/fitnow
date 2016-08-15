package com.tomkasp.fitnow.shop.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.cqrs.command.Gate;
import com.tomkasp.fitnow.shop.application.commands.PaymentsCommand;
import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.providers.PaymentFactory;
import com.tomkasp.fitnow.shop.application.providers.PaymentProvider;
import com.tomkasp.fitnow.shop.application.providers.PaymentProvidersEnum;
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
    private PaymentFactory paymentFactory;

    @Inject
    private Gate gate;


    @RequestMapping(method = RequestMethod.GET)
    @Timed
    public ResponseEntity<String> getMineDietSurvey() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        PaymentProvider payU = paymentFactory.build(PaymentProvidersEnum.PAYU);

        PaymentDetails paymentDetails = new PaymentDetails("tom", "kasp", "tomkasp@gmail.com", "1234", "1000", "desc", "123.123.123.123", "124321879");
        payU.createPaymentSignature(paymentDetails);

        log.debug("Rest request to get a diet survey");
        gate.dispatch(new PaymentsCommand());
        return ResponseEntity.ok("TEST");
    }
}
