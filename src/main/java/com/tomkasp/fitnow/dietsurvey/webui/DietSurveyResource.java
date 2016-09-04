package com.tomkasp.fitnow.dietsurvey.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.dietsurvey.application.DietSurveyFactory;
import com.tomkasp.fitnow.dietsurvey.application.DietSurveyService;
import com.tomkasp.fitnow.dietsurvey.domain.DietSurveyRepository;
import com.tomkasp.fitnow.dietsurvey.dto.DietSurveyDTO;
import com.tomkasp.fitnow.profile.application.ProfileService;
import com.tomkasp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api/dietsurveys")
public class DietSurveyResource {

    private final Logger log = LoggerFactory.getLogger(DietSurveyResource.class);
    private final DietSurveyService dietSurveyService;

    @Autowired
    public DietSurveyResource(DietSurveyRepository dietSurveyRepository, DietSurveyService dietSurveyService, DietSurveyFactory dietSurveyFactory, ProfileService profileService) {
        this.dietSurveyService = dietSurveyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Timed
    public ResponseEntity<DietSurveyDTO> getMineDietSurvey() {
        log.debug("Rest request to get a diet survey");
        final DietSurveyDTO mineDietSurvey = dietSurveyService.getMineDietSurvey();
        return Optional.ofNullable(mineDietSurvey)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DietSurveyDTO> updateDietSurvey(@Valid @RequestBody DietSurveyDTO dietSurveyDto) throws URISyntaxException, IOException {
        log.debug("REST request to diet survey : {}", dietSurveyDto);
        if (dietSurveyDto.getId() == null) {
            return createDietSurvey(dietSurveyDto);
        }
        final DietSurveyDTO savedSurvey = dietSurveyService.saveMineDietSurvey(dietSurveyDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("survey", dietSurveyDto.getId().toString()))
            .body(savedSurvey);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DietSurveyDTO> createDietSurvey(@Valid @RequestBody DietSurveyDTO dietSurveyDto) throws URISyntaxException, IOException {
        log.debug("REST request to createNew survey : {}", dietSurveyDto);
        if (dietSurveyDto.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("dietsurveys", "idexists", "A new dietsurveys cannot already have an ID")).body(null);
        }
        final DietSurveyDTO savedSurvey = dietSurveyService.saveMineDietSurvey(dietSurveyDto);
        return ResponseEntity.created(new URI("/api/dietsurveys/" + savedSurvey.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("product", savedSurvey.getId().toString()))
            .body(savedSurvey);
    }
}
