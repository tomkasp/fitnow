package com.tomkasp.fitnow.shop.application.paymentproviders.payu;


import com.tomkasp.fitnow.sharedkernel.Security;
import com.tomkasp.fitnow.shop.domain.OrderDetails;
import com.tomkasp.fitnow.shop.application.paymentproviders.PaymentProvider;
import com.tomkasp.fitnow.shop.domain.PaymentStatus;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PayUProvider implements PaymentProvider<PayUSearchCriteria> {

    private final Logger log = LoggerFactory.getLogger(PayUProvider.class);

    @Override
    public String createPaymentSignature(OrderDetails orderDetails) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        TreeMap<String, String> paramMap = new TreeMap<>();
        paramMap.putAll(PayUConstant.payUParametersMap);

        paramMap.put(PayUConstant.FIRST_NAME, orderDetails.getName());
        paramMap.put(PayUConstant.LAST_NAME, orderDetails.getSurname());
        paramMap.put(PayUConstant.EMAIL, orderDetails.getEmail());
        paramMap.put(PayUConstant.SESSION_ID, orderDetails.getOrderId());
        paramMap.put(PayUConstant.AMOUNT, orderDetails.getAmount());
        paramMap.put(PayUConstant.DESC, orderDetails.getDescription());
        paramMap.put(PayUConstant.CLIENT_IP, orderDetails.getClientIp());
        paramMap.put(PayUConstant.JS, "1");
        paramMap.put(PayUConstant.TS, orderDetails.getTimeStamp());

        return calculateSignature(paramMap);
    }


    @Override
    public PaymentStatus getPaymentStatus(PayUSearchCriteria payUSearchCriteria) throws IOException {
        return mapPayUStatusToAppStatus(sendRequest(payUSearchCriteria));
    }

    private String calculateSignature(TreeMap<String, String> paramMap) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final StrBuilder strBuilder = new StrBuilder();
        for (String key : paramMap.keySet()) {
            strBuilder.append(key.trim()).append("=").append(URLEncoder.encode(paramMap.get(key).trim(), "UTF-8")).append("&");
        }
        strBuilder.append(PayUConstant.SEC_MD5_KEY_VALUE);
        log.debug("formatted message: {}", strBuilder.toString());

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String formattedSignature = strBuilder.toString();
        md.update(formattedSignature.getBytes("UTF-8"));
        byte[] digest = md.digest();

        String format = String.format("%064x", new BigInteger(1, digest));
        log.debug("final format: {}", format);
        return format;
    }

    private PayUTranStatus sendRequest(PayUSearchCriteria payUSearchCriteria) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        String payUPaymentStatusUrl = "https://secure.payu.com/paygw/UTF/Payment/get/txt";
        String sig = Security.calculateMD5(payUSearchCriteria.getPosId() + payUSearchCriteria.getSessionId() + payUSearchCriteria.getTs() + PayUConstant.FIRST_MD5_KEY_VALUE);

        HttpPost post = new HttpPost(payUPaymentStatusUrl);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair(PayUConstant.POS_ID, payUSearchCriteria.getPosId()));
        urlParameters.add(new BasicNameValuePair(PayUConstant.SESSION_ID, payUSearchCriteria.getSessionId()));
        urlParameters.add(new BasicNameValuePair(PayUConstant.TS, payUSearchCriteria.getTs()));
        urlParameters.add(new BasicNameValuePair(PayUConstant.SIGNATURE, sig));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        HttpResponse response = client.execute(post);

        Map<String, String> receivedData = getContentFromResponseAsMap(response.getEntity().getContent());
        for (String s : receivedData.keySet()) {
            log.debug("key: {}, value {}", s, receivedData.get(s));
            if (PayUConstant.TRANS_STATUS.equals(s)) {
                final String transaction_status = receivedData.get(s);
                return PayUTranStatus.valueOf(transaction_status);
            }
        }
        return PayUTranStatus.UNKNOWN;
    }

    private PaymentStatus mapPayUStatusToAppStatus(PayUTranStatus payUTranStatus) {
        switch (payUTranStatus) {
            case NEW:
                return PaymentStatus.NEW;
            case CANCELED:
                return PaymentStatus.CANCELED;
            case REJECTED:
                return PaymentStatus.REJECTED;
            case INITIALIZED:
                return PaymentStatus.INITIALIZED;
            case WAITING:
                return PaymentStatus.WAITING;
            case REFUND:
                return PaymentStatus.REFUND;
            case PAID:
                return PaymentStatus.PAID;
            case UNKNOWN:
                return PaymentStatus.UNKNOWN;
            default:
                return PaymentStatus.UNKNOWN;
        }
    }

    private Map<String, String> getContentFromResponseAsMap(InputStream is) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        Map<String, String> map = new HashMap<>();

        String line = "";
        while ((line = rd.readLine()) != null) {
            int pos = line.indexOf(":");
            map.put(line.substring(0, pos), line.substring(pos + 1, line.length()).trim());
        }

        return map;
    }
}
