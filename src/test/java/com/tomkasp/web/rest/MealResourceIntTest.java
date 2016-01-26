package com.tomkasp.web.rest;

import com.tomkasp.Application;
import com.tomkasp.domain.Meal;
import com.tomkasp.repository.MealRepository;
import com.tomkasp.service.MealService;
import com.tomkasp.web.rest.dto.MealDTO;
import com.tomkasp.web.rest.mapper.MealMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tomkasp.domain.enumeration.MealType;

/**
 * Test class for the MealResource REST controller.
 *
 * @see MealResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MealResourceIntTest {

    
    private static final MealType DEFAULT_TYPE = MealType.Breakfast;
    private static final MealType UPDATED_TYPE = MealType.Lunch;

    @Inject
    private MealRepository mealRepository;

    @Inject
    private MealMapper mealMapper;

    @Inject
    private MealService mealService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMealMockMvc;

    private Meal meal;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MealResource mealResource = new MealResource();
        ReflectionTestUtils.setField(mealResource, "mealService", mealService);
        ReflectionTestUtils.setField(mealResource, "mealMapper", mealMapper);
        this.restMealMockMvc = MockMvcBuilders.standaloneSetup(mealResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        meal = new Meal();
        meal.setType(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createMeal() throws Exception {
        int databaseSizeBeforeCreate = mealRepository.findAll().size();

        // Create the Meal
        MealDTO mealDTO = mealMapper.mealToMealDTO(meal);

        restMealMockMvc.perform(post("/api/meals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mealDTO)))
                .andExpect(status().isCreated());

        // Validate the Meal in the database
        List<Meal> meals = mealRepository.findAll();
        assertThat(meals).hasSize(databaseSizeBeforeCreate + 1);
        Meal testMeal = meals.get(meals.size() - 1);
        assertThat(testMeal.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void getAllMeals() throws Exception {
        // Initialize the database
        mealRepository.saveAndFlush(meal);

        // Get all the meals
        restMealMockMvc.perform(get("/api/meals?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(meal.getId().intValue())))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getMeal() throws Exception {
        // Initialize the database
        mealRepository.saveAndFlush(meal);

        // Get the meal
        restMealMockMvc.perform(get("/api/meals/{id}", meal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(meal.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMeal() throws Exception {
        // Get the meal
        restMealMockMvc.perform(get("/api/meals/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeal() throws Exception {
        // Initialize the database
        mealRepository.saveAndFlush(meal);

		int databaseSizeBeforeUpdate = mealRepository.findAll().size();

        // Update the meal
        meal.setType(UPDATED_TYPE);
        MealDTO mealDTO = mealMapper.mealToMealDTO(meal);

        restMealMockMvc.perform(put("/api/meals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mealDTO)))
                .andExpect(status().isOk());

        // Validate the Meal in the database
        List<Meal> meals = mealRepository.findAll();
        assertThat(meals).hasSize(databaseSizeBeforeUpdate);
        Meal testMeal = meals.get(meals.size() - 1);
        assertThat(testMeal.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void deleteMeal() throws Exception {
        // Initialize the database
        mealRepository.saveAndFlush(meal);

		int databaseSizeBeforeDelete = mealRepository.findAll().size();

        // Get the meal
        restMealMockMvc.perform(delete("/api/meals/{id}", meal.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Meal> meals = mealRepository.findAll();
        assertThat(meals).hasSize(databaseSizeBeforeDelete - 1);
    }
}
