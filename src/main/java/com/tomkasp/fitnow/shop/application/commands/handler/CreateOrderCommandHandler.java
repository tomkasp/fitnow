package com.tomkasp.fitnow.shop.application.commands.handler;

import com.tomkasp.domain.User;
import com.tomkasp.fitnow.cqrs.annotations.CommandHandlerAnnotation;
import com.tomkasp.fitnow.cqrs.command.handler.CommandHandler;
import com.tomkasp.fitnow.shop.application.commands.CreateOrderCommand;
import com.tomkasp.fitnow.shop.domain.Order;
import com.tomkasp.fitnow.shop.infrastructure.JpaOrderRepository;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@CommandHandlerAnnotation
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand, Void> {

    private final Logger log = LoggerFactory.getLogger(CreateOrderCommandHandler.class);

    private final UserService userService;

    private final JpaOrderRepository jpaOrderRepository;

    @Inject
    public CreateOrderCommandHandler(UserService userService, JpaOrderRepository jpaOrderRepository) {
        this.userService = userService;
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    @Transactional
    public Void handle(CreateOrderCommand command) {
        final User userWithAuthorities = userService.getUserWithAuthorities();
        final Order newOrder = Order.createNew(userWithAuthorities, command.getOrderType(), command.getPaymentIntegrationId(), command.getAmount());
        //validate if order exists
        log.debug("Creating new order" );
        jpaOrderRepository.save(newOrder);
        return null;
    }

}


