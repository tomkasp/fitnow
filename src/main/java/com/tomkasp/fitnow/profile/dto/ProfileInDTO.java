package com.tomkasp.fitnow.profile.dto;

import com.tomkasp.fitnow.sharedkernel.Sex;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tomasz Kasprzycki
 */
public class ProfileInDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer weight;

    @NotNull
    private String sex;

    @NotNull
    @Max(value = 120)
    private Integer age;

    @NotNull
    @Max(value = 500)
    private Integer height;

    @NotNull
    @Max(value = 100)
    private Integer weightChangeQuantity;

    @NotNull
    private DailyActivity dailyActivity;

    @NotNull
    private Goal goal;

    private static final class DailyActivity {
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

    private static final class Goal {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public Goal setId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Goal setName(String name) {
            this.name = name;
            return this;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public DailyActivity getDailyActivity() {
        return dailyActivity;
    }

    public com.tomkasp.fitnow.profile.domain.DailyActivity getConvertedDailyActivity() {
        final Map<String, com.tomkasp.fitnow.profile.domain.DailyActivity> dailyActivity = new HashMap<>();
        dailyActivity.put("0", com.tomkasp.fitnow.profile.domain.DailyActivity.SMALL);
        dailyActivity.put("1", com.tomkasp.fitnow.profile.domain.DailyActivity.MEDIUM);
        dailyActivity.put("2", com.tomkasp.fitnow.profile.domain.DailyActivity.HIGH);
        dailyActivity.put("3", com.tomkasp.fitnow.profile.domain.DailyActivity.VERY_HIGH);
        return dailyActivity.get(this.dailyActivity.getId());
    }


    public ProfileInDTO setDailyActivity(DailyActivity dailyActivity) {
        this.dailyActivity = dailyActivity;
        return this;
    }

    public Goal getGoal() {
        return goal;
    }

    public com.tomkasp.fitnow.profile.domain.Goal getConvertedGoal(){
        final Map<String, com.tomkasp.fitnow.profile.domain.Goal> goalsMap = new HashMap<>();
        goalsMap.put("0", com.tomkasp.fitnow.profile.domain.Goal.LOOSE);
        goalsMap.put("1", com.tomkasp.fitnow.profile.domain.Goal.GAIN);
        return goalsMap.get(this.goal.getId());
    }

    public ProfileInDTO setGoal(Goal goal) {
        this.goal = goal;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeightChangeQuantity() {
        return weightChangeQuantity;
    }

    public void setWeightChangeQuantity(Integer weightChangeQuantity) {
        this.weightChangeQuantity = weightChangeQuantity;
    }

    public String getSex() {
        return sex;
    }

    public Sex getConvertedSex(){
        Map<String, Sex> sexMap = new HashMap<>();
        sexMap.put("0", Sex.MALE);
        sexMap.put("1", Sex.FEMALE);
        return sexMap.get(this.sex);
    }

    public ProfileInDTO setSex(String sex) {
        this.sex = sex;
        return this;
    }
}
