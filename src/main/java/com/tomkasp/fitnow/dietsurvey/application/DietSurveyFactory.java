package com.tomkasp.fitnow.dietsurvey.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tomkasp.fitnow.dietsurvey.domain.DietSurvey;
import com.tomkasp.fitnow.dietsurvey.dto.DietSurveyDTO;
import com.tomkasp.fitnow.dietsurvey.dto.MealQuantity;
import com.tomkasp.fitnow.profile.dto.ProfileOutDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@Component
public class DietSurveyFactory {

    private final ObjectMapper mapper = new ObjectMapper();

    public DietSurvey build(DietSurveyDTO dietSurveyDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dietSurveyDto.getMealQuantity());

        return new DietSurvey().setAdditionalInfo(dietSurveyDto.getAdditionalInfo())
            .setIsAllergy(dietSurveyDto.getIsAllergy())
            .setBornDate(dietSurveyDto.getBornDate().toDateTimeAtCurrentTime())
            .setAllergyDetails(dietSurveyDto.getAllergyDetails())
            .setFavorites(dietSurveyDto.getFavorites())
            .setFoodExclusion(dietSurveyDto.getFoodExclusion())
            .setFoodIntolleranceDetails(dietSurveyDto.getFoodIntolleranceDetails())
            .setId(dietSurveyDto.getId())
            .setIsFoodIntolerance(dietSurveyDto.getIsFoodIntolerance())
            .setIsLikingSoup(dietSurveyDto.getIsLikingSoup())
            .setIsIllness(dietSurveyDto.getIsIllness())
            .setIllnessDetails(dietSurveyDto.getIllnessDetails())
            .setPhoneNumber(dietSurveyDto.getPhoneNumber())
            .setFavorites(dietSurveyDto.getFavorites())
            .setMealQuantity(json)
            .setWakeupMax(dietSurveyDto.getWakeupMax())
            .setWakeupMin(dietSurveyDto.getWakeupMin())
            .setWorkMax(dietSurveyDto.getWorkMax())
            .setWorkMin(dietSurveyDto.getWorkMin());
    }

    public DietSurveyDTO build(DietSurvey dietSurvey, ProfileOutDTO mine) throws IOException {
        MealQuantity mealQuantityMap = mapper.readValue(dietSurvey.getMealQuantity(), MealQuantity.class);
        final DietSurveyDTO dietSurveyDTO = new DietSurveyDTO().setAdditionalInfo(dietSurvey.getAdditionalInfo())
            .setIsAllergy(dietSurvey.getIsAllergy())
            .setBornDate(dietSurvey.getBornDate().toLocalDate())
            .setAllergyDetails(dietSurvey.getAllergyDetails())
            .setFoodExclusion(dietSurvey.getFoodExclusion())
            .setFoodIntolleranceDetails(dietSurvey.getFoodIntolleranceDetails())
            .setHeight(mine.getHeight())
            .setId(dietSurvey.getId())
            .setIsFoodIntolerance(dietSurvey.getIsFoodIntolerance())
            .setIsLikingSoup(dietSurvey.getIsLikingSoup())
            .setIsIllness(dietSurvey.getIsIllness())
            .setIllnessDetails(dietSurvey.getIllnessDetails())
            .setPhoneNumber(dietSurvey.getPhoneNumber())
            .setFavorites(dietSurvey.getFavorites())
            .setMealQuantity(mealQuantityMap)
            .setWakeupMax(dietSurvey.getWakeupMax())
            .setWakeupMin(dietSurvey.getWakeupMin())
            .setWorkMax(dietSurvey.getWorkMax())
            .setSex(mine.getSex())
            .setWorkMin(dietSurvey.getWorkMin());

        return dietSurveyDTO;
    }

    DietSurveyDTO emptyDietSurveyDTO(ProfileOutDTO mine) {
        final DietSurveyDTO dietSurveyDTO = new DietSurveyDTO()
            .setHeight(mine.getHeight())
            .setSex(mine.getSex())
            .setMealQuantity(new MealQuantity());
        return dietSurveyDTO;
    }
}
