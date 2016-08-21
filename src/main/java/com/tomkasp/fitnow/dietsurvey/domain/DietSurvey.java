package com.tomkasp.fitnow.dietsurvey.domain;

import com.tomkasp.domain.User;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "diet_survey")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DietSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "born_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime bornDate;

    @Column(name = "wakeup_min")
    private Integer wakeupMin;

    @Column(name = "wakeup_max")
    private Integer wakeupMax;

    @Column(name = "work_min")
    private Integer workMin;

    @Column(name = "work_max")
    private Integer workMax;

    @Column(name = "meal_quantity")
    private String mealQuantity; // JSON based field

    @Column(name = "allergy")
    private Boolean isAllergy;

    @Column(name = "allergy_details")
    private String allergyDetails;

    @Column(name = "food_intolerance")
    private Boolean isFoodIntolerance;

    @Column(name = "food_intolerance_details")
    private String foodIntolleranceDetails;

    @Column(name = "favorites")
    private String favorites;

    @Column(name = "illness")
    private Boolean isIllness;

    @Column(name = "illness_details")
    private String illnessDetails;

    @Column(name = "soup")
    private Boolean isLikingSoup;

    @Column(name = "food_exclusion")
    private String foodExclusion;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "phone_number")
    private String phoneNumber;


    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User user;


    public Long getId() {
        return id;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public DietSurvey setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public String getAllergyDetails() {
        return allergyDetails;
    }

    public DietSurvey setAllergyDetails(String allergyDetails) {
        this.allergyDetails = allergyDetails;
        return this;
    }

    public String getFavorites() {
        return favorites;
    }

    public DietSurvey setFavorites(String favorites) {
        this.favorites = favorites;
        return this;
    }

    public String getFoodExclusion() {
        return foodExclusion;
    }

    public DietSurvey setFoodExclusion(String foodExclusion) {
        this.foodExclusion = foodExclusion;
        return this;
    }

    public String getFoodIntolleranceDetails() {
        return foodIntolleranceDetails;
    }

    public DietSurvey setFoodIntolleranceDetails(String foodIntolleranceDetails) {
        this.foodIntolleranceDetails = foodIntolleranceDetails;
        return this;
    }

    public DietSurvey setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getIsAllergy() {
        return isAllergy;
    }

    public DietSurvey setIsAllergy(Boolean isAllergy) {
        this.isAllergy = isAllergy;
        return this;
    }

    public Boolean getIsFoodIntolerance() {
        return isFoodIntolerance;
    }

    public DietSurvey setIsFoodIntolerance(Boolean isFoodIntolerance) {
        this.isFoodIntolerance = isFoodIntolerance;
        return this;
    }

    public Boolean getIsLikingSoup() {
        return isLikingSoup;
    }

    public DietSurvey setIsLikingSoup(Boolean isLikingSoup) {
        this.isLikingSoup = isLikingSoup;
        return this;
    }

    public String getMealQuantity() {
        return mealQuantity;
    }

    public DietSurvey setMealQuantity(String mealQuantity) {
        this.mealQuantity = mealQuantity;
        return this;
    }

    public Integer getWakeupMax() {
        return wakeupMax;
    }

    public DietSurvey setWakeupMax(Integer wakeupMax) {
        this.wakeupMax = wakeupMax;
        return this;
    }

    public Integer getWakeupMin() {
        return wakeupMin;
    }

    public DietSurvey setWakeupMin(Integer wakeupMin) {
        this.wakeupMin = wakeupMin;
        return this;
    }

    public Integer getWorkMax() {
        return workMax;
    }

    public DietSurvey setWorkMax(Integer workMax) {
        this.workMax = workMax;
        return this;
    }

    public Integer getWorkMin() {
        return workMin;
    }

    public DietSurvey setWorkMin(Integer workMin) {
        this.workMin = workMin;
        return this;
    }

    public User getUser() {
        return user;
    }

    public DietSurvey setUser(User user) {
        this.user = user;
        return this;
    }

    public DateTime getBornDate() {
        return bornDate;
    }

    public DietSurvey setBornDate(DateTime bornDate) {
        this.bornDate = bornDate;
        return this;
    }

    public String getIllnessDetails() {
        return illnessDetails;
    }

    public DietSurvey setIllnessDetails(String illnessDetails) {
        this.illnessDetails = illnessDetails;
        return this;
    }

    public Boolean getIsIllness() {
        return isIllness;
    }

    public DietSurvey setIsIllness(Boolean illness) {
        isIllness = illness;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DietSurvey setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
