package com.tomkasp.service;

import com.tomkasp.domain.Meal;
import com.tomkasp.web.rest.dto.MealDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Meal.
 */
public interface MealService {

    /**
     * Save a meal.
     * @return the persisted entity
     */
    public MealDTO save(MealDTO mealDTO);

    /**
     *  get all the meals.
     *  @return the list of entities
     */
    public List<MealDTO> findAll();

    /**
     *  get the "id" meal.
     *  @return the entity
     */
    public MealDTO findOne(Long id);

    /**
     *  delete the "id" meal.
     */
    public void delete(Long id);
}
