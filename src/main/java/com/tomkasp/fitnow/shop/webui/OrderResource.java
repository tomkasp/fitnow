package com.tomkasp.fitnow.shop.webui;


import com.tomkasp.fitnow.cqrs.command.Gate;
import com.tomkasp.fitnow.shop.application.commands.CreateOrderCommand;
import com.tomkasp.fitnow.shop.dto.CreateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop/orders/")
public class OrderResource {

    private final Gate gate;

    @Autowired
    public OrderResource(Gate gate) {
        this.gate = gate;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrder(@RequestBody CreateOrderDto createOrderDto) {
        gate.dispatch(new CreateOrderCommand(createOrderDto.getOrderType(), createOrderDto.getPaymentIntegrationId(), createOrderDto.getAmount()));
        //command to createNew payment and then event
    }

}

