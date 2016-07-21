package com.tomkasp.fitnow.diet.application;

import com.tomkasp.fitnow.diet.domain.DietSurvey;
import com.tomkasp.fitnow.diet.domain.DietSurveyRepository;
import com.tomkasp.fitnow.diet.dto.DietSurveyDTO;
import com.tomkasp.fitnow.profile.application.ProfileService;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    public DietSurveyService(DietSurveyRepository dietSurveyRepository, UserService userService, DietSurveyFactory dietSurveyFactory, ProfileService profileService) {
        this.dietSurveyRepository = dietSurveyRepository;
        this.userService = userService;
        this.dietSurveyFactory = dietSurveyFactory;
        this.profileService = profileService;
    }

    public DietSurveyDTO getMineDietSurvey() {
        log.debug("Request to find logged user Diet survey");
        final Long id = userService.getUserWithAuthorities().getId();
        return dietSurveyRepository.findByUserId(id)
            .map(result -> {
                    final DietSurveyDTO dietSurveyDTO = dietSurveyFactory.build(result, profileService.findMine());
                    return dietSurveyDTO;
                }
            )
            .orElse(dietSurveyFactory.emptyDietSurveyDTO());
    }

    public  DietSurveyDTO saveMineDietSurvey(DietSurveyDTO dietSurveyDTO){
        log.debug("Request to save Profile : {}", dietSurveyDTO);
        DietSurvey dietSurvey = dietSurveyFactory.build(dietSurveyDTO);
        dietSurvey.setUser(userService.getUserWithAuthorities());
        final DietSurvey savedDietSurvey = dietSurveyRepository.save(dietSurvey);
        DietSurveyDTO result = dietSurveyFactory.build(savedDietSurvey, profileService.findMine());
        return result;

    }
}
