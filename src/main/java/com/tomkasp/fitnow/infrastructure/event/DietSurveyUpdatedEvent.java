package com.tomkasp.fitnow.infrastructure.event;

import com.tomkasp.fitnow.dietsurvey.dto.DietSurveyDTO;
import com.tomkasp.fitnow.profile.domain.DailyActivity;
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
    private final DailyActivity dailyActivity;

    public DietSurveyUpdatedEvent(Integer height, Sex sex, DailyActivity dailyActivity) {

        this.height = height;
        this.sex = sex;
        this.dailyActivity = dailyActivity;
    }

    public Integer getHeight() {
        return height;
    }

    public DailyActivity getDailyActivity() {
        return dailyActivity;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "DietSurveyUpdatedEvent{" +
            "dailyActivity=" + dailyActivity +
            ", height=" + height +
            ", sex=" + sex +
            '}';
    }
}
