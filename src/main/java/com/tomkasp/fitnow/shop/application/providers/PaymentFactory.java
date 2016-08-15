package com.tomkasp.fitnow.shop.application.providers;

import com.tomkasp.fitnow.shop.application.providers.payu.PayUProvider;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactory {

    public PaymentProvider build(PaymentProvidersEnum providerName){
        if(providerName.equals(PaymentProvidersEnum.PAYU)){
            return new PayUProvider();
        }
        return null;
    }
}
