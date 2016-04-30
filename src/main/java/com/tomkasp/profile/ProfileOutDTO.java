package com.tomkasp.profile;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
public class ProfileOutDTO implements Serializable {

    private static final long serialVersionUID = -6882385919872958317L;

    private Long id;
    private Integer age;
    private Integer weight;
    private Integer height;
    private Integer weightChangeQuantity;
    private DailyActivity dailyActivity;
    private Goal goal;
    private String sex;

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

    public static class Goal {
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

    public Integer getAge() {
        return age;
    }

    public ProfileOutDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public DailyActivity getDailyActivity() {
        return dailyActivity;
    }

    public ProfileOutDTO setDailyActivity(DailyActivity dailyActivity) {
        this.dailyActivity = dailyActivity;
        return this;
    }

    public Goal getGoal() {
        return goal;
    }

    public ProfileOutDTO setGoal(Goal goal) {
        this.goal = goal;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public ProfileOutDTO setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ProfileOutDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSex() {
        return sex;
    }

    public ProfileOutDTO setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public ProfileOutDTO setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Integer getWeightChangeQuantity() {
        return weightChangeQuantity;
    }

    public ProfileOutDTO setWeightChangeQuantity(Integer weightChangeQuantity) {
        this.weightChangeQuantity = weightChangeQuantity;
        return this;
    }
}
