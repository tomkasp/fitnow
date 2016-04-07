package com.tomkasp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.domain.NutritionDetails;
import com.tomkasp.web.rest.dto.ManagedUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@RestController
@RequestMapping("/api")
public class NutritionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @RequestMapping(value = "/nutritions/{user:[_'.@a-z0-9-]+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<NutritionDetails> getUser(@PathVariable String user) {
        log.debug("REST request to get User : {}", user);
        final NutritionDetails nutritionDetails = new NutritionDetails().calories(2500).carbohydrates(60).proteins(30).fats(10);
        return new ResponseEntity<>(nutritionDetails, HttpStatus.OK);
//        return userService.getUserWithAuthoritiesByLogin(login)
//            .map(ManagedUserDTO::new)
//            .map(managedUserDTO -> new ResponseEntity<>(managedUserDTO, HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
