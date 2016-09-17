package com.tomkasp.fitnow.shop.application.paymentproviders.payu;

import org.junit.Test;

/**
 * Created by tomkasp on 10/09/16.
 */
public class PayUTransactionStatusTest {


    @Test
    public void testTransactionStatusEnum(){
        System.out.println(PayUTransactionStatus.findByString("99"));
    }

}
