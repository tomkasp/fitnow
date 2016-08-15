package com.tomkasp.fitnow.shop.application.commands;

import com.tomkasp.fitnow.cqrs.annotations.CommandHandlerAnnotation;
import com.tomkasp.fitnow.cqrs.command.handler.CommandHandler;
import com.tomkasp.fitnow.shop.webui.PaymentsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CommandHandlerAnnotation
public class PaymentsCommandHandler implements CommandHandler<PaymentsCommand, Void> {

    private final Logger log = LoggerFactory.getLogger(PaymentsResource.class);

    @Override
    public Void handle(PaymentsCommand command) {
        log.info("HANDDDDLER!!!!!!!!");
        return null;
    }
}
