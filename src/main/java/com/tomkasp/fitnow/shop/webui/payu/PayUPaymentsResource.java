package com.tomkasp.fitnow.shop.webui.payu;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.cqrs.command.Gate;
import com.tomkasp.fitnow.shop.application.providers.PaymentFactory;
import com.tomkasp.fitnow.shop.application.providers.PaymentProvider;
import com.tomkasp.fitnow.shop.application.providers.payu.PayUSearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;

@RestController
public class PayUPaymentsResource {

    private final Logger log = LoggerFactory.getLogger(PayUPaymentsResource.class);

    private final PaymentFactory paymentFactory;

    private final Gate gate;

    @Inject
    public PayUPaymentsResource(PaymentFactory paymentFactory, Gate gate) {
        this.paymentFactory = paymentFactory;
        this.gate = gate;
    }


//    public ResponseEntity<OrderDetails> getOrderSignature() throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        final OrderDetails paymentDetails = orderDetailsFinder.getOrderDetails();
//        log.debug("Payment details value: {}", paymentDetails);
//        gate.dispatch(new PaymentsCommand());
//        return ResponseEntity.ok(null);
//    }

    @RequestMapping(value = "/payupayments", method = RequestMethod.POST)
    @Timed
    @ResponseStatus(HttpStatus.OK)
    public String updatePayUPaymentStatus(@RequestParam("pos_id") String posId, @RequestParam("session_id") String sessionId, @RequestParam("ts") String ts, @RequestParam("sig") String sig) throws IOException {

        log.info("Payment posid: {}, sessionId: {}, ts: {}, sig: {}", posId, sessionId, ts, sig);
        final PaymentProvider<PayUSearchCriteria> paymentProvider = paymentFactory.build();
        paymentProvider.getPaymentStatus(new PayUSearchCriteria(posId, sessionId, ts, sig));
        return "OK";
    }


}



