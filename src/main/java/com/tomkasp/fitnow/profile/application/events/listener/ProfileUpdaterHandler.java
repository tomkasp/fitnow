package com.tomkasp.fitnow.profile.application.events.listener;

import com.tomkasp.fitnow.infrastructure.event.DietSurveyUpdatedEvent;
import com.tomkasp.fitnow.profile.application.ProfileService;
import com.tomkasp.repository.ProfileRepository;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author Tomasz Kasprzycki
 */
@Component
@Transactional(readOnly = false)
public class ProfileUpdaterHandler {

    private final Logger log = LoggerFactory.getLogger(ProfileUpdaterHandler.class);
    private final ProfileRepository productRepository;
    private final ProfileService profileService;

    @Autowired
    public ProfileUpdaterHandler(ProfileRepository productRepository, ProfileService profileService) {
        this.productRepository = productRepository;

        this.profileService = profileService;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handler(DietSurveyUpdatedEvent dietSurveyUpdatedEvent) {
        log.debug("Diet survey update event: {}", dietSurveyUpdatedEvent);
        profileService.updateProfileAfterEvent(dietSurveyUpdatedEvent.getHeight(), dietSurveyUpdatedEvent.getSex());
    }
}
