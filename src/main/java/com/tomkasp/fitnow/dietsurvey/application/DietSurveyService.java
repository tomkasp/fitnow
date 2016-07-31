package com.tomkasp.fitnow.dietsurvey.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tomkasp.fitnow.infrastructure.event.DietSurveyUpdatedEvent;
import com.tomkasp.fitnow.dietsurvey.domain.DietSurvey;
import com.tomkasp.fitnow.dietsurvey.domain.DietSurveyRepository;
import com.tomkasp.fitnow.dietsurvey.dto.DietSurveyDTO;
import com.tomkasp.fitnow.infrastructure.event.EventPublisher;
import com.tomkasp.fitnow.profile.application.ProfileService;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Transactional(readOnly = false)
public class DietSurveyService {

    private final Logger log = LoggerFactory.getLogger(DietSurvey.class);
    private final DietSurveyRepository dietSurveyRepository;
    private final UserService userService;
    private final DietSurveyFactory dietSurveyFactory;
    //TODO move this to http request
    private final ProfileService profileService;
    private final EventPublisher eventPublisher;

    @Autowired
    public DietSurveyService(DietSurveyRepository dietSurveyRepository, UserService userService, DietSurveyFactory dietSurveyFactory, ProfileService profileService, EventPublisher eventPublisher) {
        this.dietSurveyRepository = dietSurveyRepository;
        this.userService = userService;
        this.dietSurveyFactory = dietSurveyFactory;
        this.profileService = profileService;
        this.eventPublisher = eventPublisher;
    }

    public DietSurveyDTO getMineDietSurvey() {
        log.debug("Request to find logged user Diet survey");
        final Long id = userService.getUserWithAuthorities().getId();
        return dietSurveyRepository.findByUserId(id)
            .map(result -> {
                    DietSurveyDTO dietSurveyDTO = null;
                    try {
                        dietSurveyDTO = dietSurveyFactory.build(result, profileService.findMine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return dietSurveyDTO;
                }
            )
            .orElse(dietSurveyFactory.emptyDietSurveyDTO(profileService.findMine()));
    }

    public  DietSurveyDTO saveMineDietSurvey(DietSurveyDTO dietSurveyDTO) throws IOException {
        log.debug("Request to save Profile : {}", dietSurveyDTO);
        DietSurvey dietSurvey = dietSurveyFactory.build(dietSurveyDTO);
        dietSurvey.setUser(userService.getUserWithAuthorities());
        final DietSurvey savedDietSurvey = dietSurveyRepository.save(dietSurvey);
        DietSurveyDTO result = dietSurveyFactory.build(savedDietSurvey, profileService.findMine());
        eventPublisher.publish(new DietSurveyUpdatedEvent(dietSurveyDTO.getHeight(), dietSurveyDTO.getConvertedSex(), dietSurveyDTO.getConvertedDailyActivity()));
        return result;

    }
}
