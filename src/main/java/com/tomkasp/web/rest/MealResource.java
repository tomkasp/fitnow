package com.tomkasp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.domain.Meal;
import com.tomkasp.service.MealService;
import com.tomkasp.web.rest.util.HeaderUtil;
import com.tomkasp.web.rest.dto.MealDTO;
import com.tomkasp.web.rest.mapper.MealMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Meal.
 */
@RestController
@RequestMapping("/api")
public class MealResource {

    private final Logger log = LoggerFactory.getLogger(MealResource.class);
        
    @Inject
    private MealService mealService;
    
    @Inject
    private MealMapper mealMapper;
    
    /**
     * POST  /meals -> Create a new meal.
     */
    @RequestMapping(value = "/meals",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MealDTO> createMeal(@RequestBody MealDTO mealDTO) throws URISyntaxException {
        log.debug("REST request to save Meal : {}", mealDTO);
        if (mealDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("meal", "idexists", "A new meal cannot already have an ID")).body(null);
        }
        MealDTO result = mealService.save(mealDTO);
        return ResponseEntity.created(new URI("/api/meals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("meal", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /meals -> Updates an existing meal.
     */
    @RequestMapping(value = "/meals",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MealDTO> updateMeal(@RequestBody MealDTO mealDTO) throws URISyntaxException {
        log.debug("REST request to update Meal : {}", mealDTO);
        if (mealDTO.getId() == null) {
            return createMeal(mealDTO);
        }
        MealDTO result = mealService.save(mealDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("meal", mealDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /meals -> get all the meals.
     */
    @RequestMapping(value = "/meals",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<MealDTO> getAllMeals() {
        log.debug("REST request to get all Meals");
        return mealService.findAll();
            }

    /**
     * GET  /meals/:id -> get the "id" meal.
     */
    @RequestMapping(value = "/meals/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MealDTO> getMeal(@PathVariable Long id) {
        log.debug("REST request to get Meal : {}", id);
        MealDTO mealDTO = mealService.findOne(id);
        return Optional.ofNullable(mealDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /meals/:id -> delete the "id" meal.
     */
    @RequestMapping(value = "/meals/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        log.debug("REST request to delete Meal : {}", id);
        mealService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("meal", id.toString())).build();
    }
}
