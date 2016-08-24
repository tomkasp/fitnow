package com.tomkasp.fitnow.shop.application.providers.payu;


import com.tomkasp.fitnow.shop.application.domain.PaymentDetails;
import com.tomkasp.fitnow.shop.application.providers.PaymentProvider;
import com.tomkasp.fitnow.shop.webui.PaymentsResource;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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

    @Override
    public String getPaymentStatus(String paymentIdentifier) {
        return null;
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

    private void sendRequest(String posId, String sessionId, String ts, String hashed) throws IOException {
        String url = "https://secure.payu.com/paygw/UTF/Payment/get/txt";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("pos_id", posId));
        urlParameters.add(new BasicNameValuePair("session_id", sessionId));


        urlParameters.add(new BasicNameValuePair("ts", ts));

        urlParameters.add(new BasicNameValuePair("sig", hashed));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        HttpResponse response = client.execute(post);

        System.out.println(response);

        Map<String, String> receivedData = getContentFromResponseAsMap(response.getEntity().getContent());
        for (String s : receivedData.keySet()) {
            log.debug("key: {}, value {}", s, receivedData.get(s));
        }


    }

    private Map<String, String> getContentFromResponseAsMap(InputStream is) throws IOException {

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        Map<String, String> map = new HashMap<>();

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            int pos = line.indexOf(":");
            map.put(line.substring(0, pos), line.substring(pos + 1, line.length()).trim());
        }

        return map;
    }
}
