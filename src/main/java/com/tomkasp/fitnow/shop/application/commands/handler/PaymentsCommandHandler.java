package com.tomkasp.fitnow.shop.application.commands.handler;

import com.tomkasp.fitnow.cqrs.annotations.CommandHandlerAnnotation;
import com.tomkasp.fitnow.cqrs.command.handler.CommandHandler;
import com.tomkasp.fitnow.shop.application.commands.PaymentsCommand;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@CommandHandlerAnnotation
public class PaymentsCommandHandler implements CommandHandler<PaymentsCommand, Void> {

    private final Logger log = LoggerFactory.getLogger(PaymentsCommandHandler.class);

    @Inject
    private UserService userService;

    @Override
    public Void handle(PaymentsCommand command) {

        log.info("HANDDDDLER!!!!!!!!");
        return null;
    }
}
