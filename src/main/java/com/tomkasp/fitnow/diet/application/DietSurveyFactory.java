package com.tomkasp.fitnow.diet.application;

import com.tomkasp.fitnow.diet.domain.DietSurvey;
import com.tomkasp.fitnow.diet.dto.DietSurveyDTO;
import com.tomkasp.fitnow.profile.dto.ProfileOutDTO;
import com.tomkasp.fitnow.sharedkernel.Sex;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@Component
public class DietSurveyFactory {
    public DietSurvey build(DietSurveyDTO dietSurveyDto) {
        return new DietSurvey().setAdditionalInfo(dietSurveyDto.getAdditionalInfo())
            .setIsAllergy(dietSurveyDto.getIsAllergy())
            .setBornDate(dietSurveyDto.getBornDate())
            .setAllergyDetails(dietSurveyDto.getAllergyDetails())
            .setFavorites(dietSurveyDto.getFavorites())
            .setFoodExclusion(dietSurveyDto.getFoodExclusion())
            .setFoodIntolleranceDetails(dietSurveyDto.getFoodIntolleranceDetails())
            .setHeight(dietSurveyDto.getHeight())
            .setId(dietSurveyDto.getId())
            .setIsFoodIntolerance(dietSurveyDto.getIsFoodIntolerance())
            .setHeight(dietSurveyDto.getHeight())
            .setIsLikingSoup(dietSurveyDto.getIsLikingSoup())
            .setFavorites(dietSurveyDto.getFavorites())
            .setMealQuantity(dietSurveyDto.getMealQuantity())
            .setWakeupMax(dietSurveyDto.getWakeupMax())
            .setWakeupMin(dietSurveyDto.getWakeupMin())
            .setWorkMax(dietSurveyDto.getWorkMax())
            .setWorkMin(dietSurveyDto.getWorkMin());
    }

    public DietSurveyDTO build(DietSurvey dietSurvey, ProfileOutDTO mine) {
        final DietSurveyDTO dietSurveyDTO = new DietSurveyDTO().setAdditionalInfo(dietSurvey.getAdditionalInfo())
            .setIsAllergy(dietSurvey.getIsAllergy())
            .setBornDate(dietSurvey.getBornDate())
            .setAllergyDetails(dietSurvey.getAllergyDetails())
            .setFavorites(dietSurvey.getFavorites())
            .setFoodExclusion(dietSurvey.getFoodExclusion())
            .setFoodIntolleranceDetails(dietSurvey.getFoodIntolleranceDetails())
            .setHeight(dietSurvey.getHeight())
            .setId(dietSurvey.getId())
            .setIsFoodIntolerance(dietSurvey.getIsFoodIntolerance())
            .setHeight(dietSurvey.getHeight())
            .setIsLikingSoup(dietSurvey.getIsLikingSoup())
            .setFavorites(dietSurvey.getFavorites())
            .setMealQuantity(dietSurvey.getMealQuantity())
            .setWakeupMax(dietSurvey.getWakeupMax())
            .setWakeupMin(dietSurvey.getWakeupMin())
            .setWorkMax(dietSurvey.getWorkMax())
            .setSex(mine.getSex())
            .setWorkMin(dietSurvey.getWorkMin());
        ofNullable(mine.getDailyActivity()).map(result ->
                dietSurveyDTO.setDailyActivity(new DietSurveyDTO.DailyActivity().setName(result.getName()).setId(result.getId().toString()))
        );

        return dietSurveyDTO;
    }

    public DietSurveyDTO emptyDietSurveyDTO() {
        return new DietSurveyDTO();
    }

    private Sex convertSex(String sex) {
        if ("0".equals(sex)) {
            return Sex.MALE;
        }
        return Sex.FEMALE;
    }
}
