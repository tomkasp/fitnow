package com.tomkasp.fitnow.shop.application.providers.payu;


import java.util.TreeMap;

public class PayUConstant {

    public static final TreeMap<String, String> payUParametersMap = new TreeMap<>();

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String POS_ID = "pos_id";
    public static final String POS_AUT_KEY = "pos_auth_key";
    public static final String SESSION_ID = "session_id";
    public static final String AMOUNT = "amount";
    public static final String DESC = "desc";
    public static final String CLIENT_IP = "client_ip";
    public static final String JS = "js";
    public static final String TS = "ts";

    public static final String SEC_MD5_KEY_VALUE = "41d00404e751dab605c618d61109807d";
    public static final String POS_ID_VALUE = "218888";
    public static final String POS_AUT_KEY_VALUE = "rPqpYXV";

    static {
        payUParametersMap.put(POS_ID, POS_ID_VALUE);
        payUParametersMap.put(POS_AUT_KEY, POS_AUT_KEY_VALUE);
        payUParametersMap.put(JS, "1");
    }

}
