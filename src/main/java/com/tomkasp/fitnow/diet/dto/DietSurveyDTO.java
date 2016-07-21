package com.tomkasp.fitnow.diet.dto;

import org.joda.time.DateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class DietSurveyDTO {

    private Long id;
    private Integer height;
    private DailyActivity dailyActivity;
    private DateTime bornDate;
    private String sex;
    private Integer wakeupMin;
    private Integer wakeupMax;
    private Integer workMin;
    private Integer workMax;
    private String mealQuantity; // JSON based field
    private Boolean isAllergy;
    private String allergyDetails;
    private Boolean isFoodIntolerance;
    private String foodIntolleranceDetails;
    private String favorites;
    private Boolean isLikingSoup;
    private String foodExclusion;
    private String additionalInfo;

    public static class DailyActivity {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public DailyActivity setId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public DailyActivity setName(String name) {
            this.name = name;
            return this;
        }
    }

    public DailyActivity getDailyActivity() {
        return dailyActivity;
    }

    public DietSurveyDTO setDailyActivity(DailyActivity dailyActivity) {
        this.dailyActivity = dailyActivity;
        return this;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public DietSurveyDTO setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public DateTime getBornDate() {
        return bornDate;
    }

    public DietSurveyDTO setBornDate(DateTime bornDate) {
        this.bornDate = bornDate;
        return this;
    }

    public String getAllergyDetails() {
        return allergyDetails;
    }

    public DietSurveyDTO setAllergyDetails(String allergyDetails) {
        this.allergyDetails = allergyDetails;
        return this;
    }

    public String getFavorites() {
        return favorites;
    }

    public DietSurveyDTO setFavorites(String favorites) {
        this.favorites = favorites;
        return this;
    }

    public String getFoodExclusion() {
        return foodExclusion;
    }

    public DietSurveyDTO setFoodExclusion(String foodExclusion) {
        this.foodExclusion = foodExclusion;
        return this;
    }

    public String getFoodIntolleranceDetails() {
        return foodIntolleranceDetails;
    }

    public DietSurveyDTO setFoodIntolleranceDetails(String foodIntolleranceDetails) {
        this.foodIntolleranceDetails = foodIntolleranceDetails;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public DietSurveyDTO setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DietSurveyDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getIsAllergy() {
        return isAllergy;
    }

    public DietSurveyDTO setIsAllergy(Boolean isAllergy) {
        this.isAllergy = isAllergy;
        return this;
    }

    public Boolean getIsFoodIntolerance() {
        return isFoodIntolerance;
    }

    public DietSurveyDTO setIsFoodIntolerance(Boolean isFoodIntolerance) {
        this.isFoodIntolerance = isFoodIntolerance;
        return this;
    }

    public Boolean getIsLikingSoup() {
        return isLikingSoup;
    }

    public DietSurveyDTO setIsLikingSoup(Boolean isLikingSoup) {
        this.isLikingSoup = isLikingSoup;
        return this;
    }

    public String getMealQuantity() {
        return mealQuantity;
    }

    public DietSurveyDTO setMealQuantity(String mealQuantity) {
        this.mealQuantity = mealQuantity;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public DietSurveyDTO setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Integer getWakeupMax() {
        return wakeupMax;
    }

    public DietSurveyDTO setWakeupMax(Integer wakeupMax) {
        this.wakeupMax = wakeupMax;
        return this;
    }

    public Integer getWakeupMin() {
        return wakeupMin;
    }

    public DietSurveyDTO setWakeupMin(Integer wakeupMin) {
        this.wakeupMin = wakeupMin;
        return this;
    }

    public Integer getWorkMax() {
        return workMax;
    }

    public DietSurveyDTO setWorkMax(Integer workMax) {
        this.workMax = workMax;
        return this;
    }

    public Integer getWorkMin() {
        return workMin;
    }

    public DietSurveyDTO setWorkMin(Integer workMin) {
        this.workMin = workMin;
        return this;
    }
}
