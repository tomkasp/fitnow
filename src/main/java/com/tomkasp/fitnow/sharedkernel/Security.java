package com.tomkasp.fitnow.sharedkernel;

/**
 * Created by tomkasp on 24/08/16.
 */
public class Security {

    public static String calculateMD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toString((array[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
