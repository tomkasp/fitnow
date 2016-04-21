package com.tomkasp.profile;

import com.tomkasp.domain.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private Goal goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "daily_activity")
    private DailyActivity dailyActivity;

    @Column(name = "weight")
    private Integer bodyWeight;

    @Column(name = "age")
    private Integer age;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight_difference")
    private Integer weightDifference;

    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User user;

    public Integer getAge() {
        return age;
    }

    public Profile setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getBodyWeight() {
        return bodyWeight;
    }

    public Profile setBodyWeight(Integer bodyWeight) {
        this.bodyWeight = bodyWeight;
        return this;
    }

    public DailyActivity getDailyActivity() {
        return dailyActivity;
    }

    public Profile setDailyActivity(DailyActivity dailyActivity) {
        this.dailyActivity = dailyActivity;
        return this;
    }

    public Goal getGoal() {
        return goal;
    }

    public Profile setGoal(Goal goal) {
        this.goal = goal;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public Profile setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public Sex getSex() {
        return sex;
    }

    public Profile setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public Integer getWeightDifference() {
        return weightDifference;
    }

    public Profile setWeightDifference(Integer weightDifference) {
        this.weightDifference = weightDifference;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Profile setUser(User user) {
        this.user = user;
        return this;
    }
}
