package com.tomkasp.domain;

import javax.persistence.Column;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
public class NutritionDetails {

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "proteins")
    private Integer proteins;

    @Column(name = "fats")
    private Integer fats;

    @Column(name = "carbohydrates")
    private Integer carbohydrates;

    public NutritionDetails calories(final Integer calories) {
        this.calories = calories;
        return this;
    }

    public NutritionDetails proteins(final Integer proteins) {
        this.proteins = proteins;
        return this;
    }

    public NutritionDetails fats(final Integer fats) {
        this.fats = fats;
        return this;
    }

    public NutritionDetails carbohydrates(final Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
        return this;
    }


    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }
}
