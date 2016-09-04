package com.tomkasp.fitnow.shop.webui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/shop/payments/")
public class PaymentResource {

    @RequestMapping(method = RequestMethod.GET)
    public void getPaymentsOfUser() {
            //todo only user specific payments - move to read model
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createPayment() {
            //command to createNew payment and then event
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updatePayment() {
            //command to update payment and then event
    }
}
