package com.tomkasp.service.impl;

import com.tomkasp.service.MealService;
import com.tomkasp.domain.Meal;
import com.tomkasp.repository.MealRepository;
import com.tomkasp.web.rest.dto.MealDTO;
import com.tomkasp.web.rest.mapper.MealMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Meal.
 */
@Service
@Transactional
public class MealServiceImpl implements MealService{

    private final Logger log = LoggerFactory.getLogger(MealServiceImpl.class);
    
    @Inject
    private MealRepository mealRepository;
    
    @Inject
    private MealMapper mealMapper;
    
    /**
     * Save a meal.
     * @return the persisted entity
     */
    public MealDTO save(MealDTO mealDTO) {
        log.debug("Request to save Meal : {}", mealDTO);
        Meal meal = mealMapper.mealDTOToMeal(mealDTO);
        meal = mealRepository.save(meal);
        MealDTO result = mealMapper.mealToMealDTO(meal);
        return result;
    }

    /**
     *  get all the meals.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<MealDTO> findAll() {
        log.debug("Request to get all Meals");
        List<MealDTO> result = mealRepository.findAllWithEagerRelationships().stream()
            .map(mealMapper::mealToMealDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one meal by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public MealDTO findOne(Long id) {
        log.debug("Request to get Meal : {}", id);
        Meal meal = mealRepository.findOneWithEagerRelationships(id);
        MealDTO mealDTO = mealMapper.mealToMealDTO(meal);
        return mealDTO;
    }

    /**
     *  delete the  meal by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Meal : {}", id);
        mealRepository.delete(id);
    }
}
