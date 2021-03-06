package com.tomkasp.fitnow.shop.application.commands.handler;

import com.tomkasp.fitnow.cqrs.annotations.CommandHandlerAnnotation;
import com.tomkasp.fitnow.cqrs.command.handler.CommandHandler;
import com.tomkasp.fitnow.shop.application.commands.UpdatePaymentStatusCommand;
import com.tomkasp.fitnow.shop.domain.Payment;
import com.tomkasp.fitnow.shop.infrastructure.JpaPaymentRepository;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@CommandHandlerAnnotation
public class UpdatePaymentStatusCommandHandler implements CommandHandler<UpdatePaymentStatusCommand, Void> {

    private final Logger log = LoggerFactory.getLogger(UpdatePaymentStatusCommandHandler.class);

    private final UserService userService;
    private final JpaPaymentRepository jpaPaymentRepository;

    @Autowired
    public UpdatePaymentStatusCommandHandler(UserService userService, JpaPaymentRepository jpaPaymentRepository) {
        this.userService = userService;
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    @Override
    @Transactional
    public Void handle(UpdatePaymentStatusCommand command) {
        final Payment paymentToUpdate = jpaPaymentRepository.findByIntegrationId(command.getPaymentIntegrationId());
        Optional.ofNullable(paymentToUpdate).map(result -> {
            result.changePaymentStatus(command.getNewPaymentStatus());
            log.debug("updating payment status with status: {}", command.getNewPaymentStatus());
            return jpaPaymentRepository.save(result);

        });
        return null;
    }
}
