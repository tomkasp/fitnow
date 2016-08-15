package com.tomkasp.fitnow.shop.application.providers.payu;


import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.providers.PaymentProvider;
import com.tomkasp.fitnow.shop.webui.PaymentsResource;
import org.apache.commons.lang.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import static com.tomkasp.fitnow.shop.application.providers.payu.PayUConstant.*;

public class PayUProvider implements PaymentProvider {

    private final Logger log = LoggerFactory.getLogger(PayUProvider.class);

    @Override
    public String createPaymentSignature(PaymentDetails paymentDetails) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        TreeMap<String, String> paramMap = new TreeMap<>();
        paramMap.putAll(payUParametersMap);

        paramMap.put(FIRST_NAME, paymentDetails.getName());
        paramMap.put(LAST_NAME, paymentDetails.getSurname());
        paramMap.put(EMAIL, paymentDetails.getEmail());
        paramMap.put(SESSION_ID, paymentDetails.getSessionId());
        paramMap.put(AMOUNT, paymentDetails.getAmount());
        paramMap.put(DESC, paymentDetails.getDescription());
        paramMap.put(CLIENT_IP, paymentDetails.getClientIp());
        paramMap.put(JS, "1");
        paramMap.put(TS, paymentDetails.getTimeStamp());

        return calculateSignature(paramMap);
    }

    private String calculateSignature(TreeMap<String, String> paramMap) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final StrBuilder strBuilder = new StrBuilder();
        for (String key : paramMap.keySet()) {
            strBuilder.append(key.trim()).append("=").append(URLEncoder.encode(paramMap.get(key).trim(), "UTF-8")).append("&");
        }
        strBuilder.append(SEC_MD5_KEY_VALUE);
        log.debug("formatted message: {}", strBuilder.toString());

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String formattedSignature = strBuilder.toString();
        md.update(formattedSignature.getBytes("UTF-8"));
        byte[] digest = md.digest();

        String format = String.format("%064x", new BigInteger(1, digest));
        log.debug("final format: {}", format);
        return format;
    }




//    //            payUParametersMap.put(FIRST_NAME, "tom");
////            payUParametersMap.put("last_name", "kasp");
////            payUParametersMap.put("email", "tomkasp@gmail.com");
////            payUParametersMap.put("session_id", "1234");
//            payUParametersMap.put("amount", "1000");
//            payUParametersMap.put("desc", "desc");
//            payUParametersMap.put("client_ip", "123.123.123.123");
//            payUParametersMap.put("ts", "124321879");
}
