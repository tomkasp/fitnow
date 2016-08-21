package com.tomkasp.fitnow.infrastructure.event;

import com.tomkasp.fitnow.sharedkernel.Sex;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class DietSurveyUpdatedEvent implements Serializable{

    private UUID id;
    private final Integer height;
    private final Sex sex;

    public DietSurveyUpdatedEvent(Integer height, Sex sex) {

        this.height = height;
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "DietSurveyUpdatedEvent{" +
            ", height=" + height +
            ", sex=" + sex +
            '}';
    }
}
