package com.tomkasp.fitnow.shop.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.cqrs.command.Gate;
import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.readmodel.PaymentDetailsFinder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.tomkasp.fitnow.sharedkernel.Security.calculateMD5;

@RestController
public class PaymentsResource {

    private final Logger log = LoggerFactory.getLogger(PaymentsResource.class);

    @Inject
    private PaymentDetailsFinder paymentDetailsFinder;

    @Inject
    private Gate gate;


    @RequestMapping(value = "/api/payments", method = RequestMethod.GET)
    @Timed
    public ResponseEntity<PaymentDetails> getPaymentSignature() throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        final PaymentDetails paymentDetails = paymentDetailsFinder.getPaymentDetails();
//        log.debug("Payment details value: {}", paymentDetails);
//        gate.dispatch(new PaymentsCommand());
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/payments", method = RequestMethod.POST)
    @Timed
    @ResponseStatus(HttpStatus.OK)
    public String updatePayUPaymentStatus(@RequestParam("pos_id") String posId, @RequestParam("session_id") String sessionId, @RequestParam("ts") String ts, @RequestParam("sig") String sig) throws IOException {

        log.info("Payment posid: {}, sessionId: {}, ts: {}, sig: {}", posId, sessionId, ts, sig);

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://secure.payu.com/paygw/UTF/Payment/get";

        String toHash = posId + sessionId + ts + "ed7e332114fe5e71c43cb3745ce3bcc6";


        log.debug("to hash value: {}", toHash);

        String hashed = calculateMD5(toHash);

        log.debug("hashed value: {}", hashed);


        String key2 = "41d00404e751dab605c618d61109807d";
        String test = posId + sessionId + ts + key2;
        String testHased = calculateMD5(test);
        log.debug("hased by my algorithm: {}, ", testHased);


//        sendRequest(posId, sessionId,ts, hashed);



        return "OK";
    }








}



