package com.tomkasp.web;

import com.payu.sdk.PayU;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Language;
import org.apache.commons.lang.text.StrBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

/**
 * Created by tomkasp on 13/08/16.
 */
public class PayUtest {

static {


    PayU.apiKey = "xxxxxxxxxxxx"; //Enter your ApiKey here.
    PayU.apiLogin = "xxxxxxxxxxxx"; //Enter your apiLogin here.
    PayU.language = Language.en; //Enter the language you prefer here
    PayU.isTest = false; //Leave it true when testing..
//    PayU.paymentsUrl = "https://api.payulatam.com/payments-api/"; //Include it only if you want to test a specific payment server, and display the route of the same.
//    PayU.reportsUrl = "https://api.payulatam.com/reports-api/"; //Include it only if you want to test it in a specific query server, and indicate the path of the same.
}

//                <form method="post" action="https://secure.payu.com/api/v2_1/orders">
//                    <input type="hidden" name="continueUrl" value="http://shop.url/continue">
//                    <input type="hidden" name="currencyCode" value="PLN">
//                    <input type="hidden" name="customerIp" value="123.123.123.123">
//                    <input type="hidden" name="description" value="Order description">
//                    <input type="hidden" name="merchantPosId" value="218888">
//                    <input type="hidden" name="notifyUrl" value="http://shop.url/notify">
//                    <input type="hidden" name="products[0].name" value="Product 1">
//                    <input type="hidden" name="products[0].quantity" value="1">
//                    <input type="hidden" name="products[0].unitPrice" value="1000">
//                    <input type="hidden" name="totalAmount" value="1000">
//                    <input type="hidden" name="OpenPayu-Signature" value="sender=145227;algorithm=SHA-256;signature=bc94a8026d6032b5e216be112a5fb7544e66e23e68d44b4283ff495bdb3983a8">
//                    <button type="submit" class="btn btn-info font-bold m" formtarget="_blank" >Kup i zaplac</button>
//                </form >
    public void test() throws ConnectionException, InvalidParametersException, PayUException {



    }


    public static void main(String[] args) throws ConnectionException, InvalidParametersException, PayUException, UnsupportedEncodingException, NoSuchAlgorithmException {

        TreeMap<String, String> paramMap = new TreeMap<>();
        paramMap.put("first_name", "tom");
        paramMap.put("last_name", "kasp");
        paramMap.put("email", "tomkasp@gmail.com");
        paramMap.put("pos_id", "218888");
        paramMap.put("pos_auth_key", "rPqpYXV");
        paramMap.put("session_id", "1234");
        paramMap.put("amount", "1000");
        paramMap.put("desc", "desc");
        paramMap.put("client_ip", "123.123.123.123");
        paramMap.put("js", "1");
        paramMap.put("ts", "124321879");


        StrBuilder strBuilder = new StrBuilder();
        for (String key : paramMap.keySet()) {
            strBuilder.append(key.trim()).append("=").append(URLEncoder.encode(paramMap.get(key).trim(), "UTF-8")).append("&");
        }

        strBuilder.append("41d00404e751dab605c618d61109807d");


        System.out.println(strBuilder);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String text = strBuilder.toString();

        md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();

        System.out.println(String.format("%064x", new java.math.BigInteger(1, digest)));




    }
}

//amount=1000&client_ip=123.123.123.123&desc=desc&email=tomkasp%40gmai.com&first_name=tom&js=1&last_name=kasp&pos_auth_key=rPqpYXV&pos_id=218888&session_id=1234&ts=124321879&41d00404e751dab605c618d61109807d
//amount=1000&client_ip=123.123.123.123&desc=desc&email=tomkasp%40gmail.com&first_name=tom&js=1&last_name=kasp&pos_auth_key=rPqpYXV&pos_id=218888&session_id=1234&ts=124321879&41d00404e751dab605c618d61109807d
//
//
//
//    2016-08-13 14:42:51.064 DEBUG 5785 --- [nio-8081-exec-1] c.t.f.s.a.providers.payu.PayUProvider    : final format: 6a3177a2454e268f5906587990680eca521a83657298d0876f3f4ba8840937bc
//    2016-08-13 14:42:51.064 DEBUG 5785 --- [nio-8081-exec-1] c.t.fitnow.shop.webui.PaymentsResource   :
