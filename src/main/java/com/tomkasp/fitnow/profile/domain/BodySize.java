package com.tomkasp.fitnow.profile.domain;

import com.tomkasp.domain.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "body_size")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BodySize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "waist")
    private Integer waist;

    @Column(name = "thigh")
    private Integer thigh;

    @Column(name = "chest")
    private Integer chest;

    @Column(name = "hip")
    private Integer hip;

    @Column(name = "arm")
    private Integer arm;

    @Column(name = "neck")
    private Integer neck;

    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "BODY_SIZE_ID", nullable = false)
    @Fetch(FetchMode.JOIN)
    @OrderBy("date ASC")
    private List<BodySizeHistory> bodySizeHistories = new ArrayList<>();


    public Integer getArm() {
        return arm;
    }

    public BodySize setArm(Integer arm) {
        this.arm = arm;
        return this;
    }

    public Integer getChest() {
        return chest;
    }

    public BodySize setChest(Integer chest) {
        this.chest = chest;
        return this;
    }

    public Integer getHip() {
        return hip;
    }

    public BodySize setHip(Integer hip) {
        this.hip = hip;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BodySize setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNeck() {
        return neck;
    }

    public BodySize setNeck(Integer neck) {
        this.neck = neck;
        return this;
    }

    public User getUser() {
        return user;
    }

    public BodySize setUser(User user) {
        this.user = user;
        return this;
    }

    public Integer getThigh() {
        return thigh;
    }

    public BodySize setThigh(Integer thigh) {
        this.thigh = thigh;
        return this;
    }

    public Integer getWaist() {
        return waist;
    }

    public BodySize setWaist(Integer waist) {
        this.waist = waist;
        return this;
    }

    public List<BodySizeHistory> getBodySizeHistories() {
        return bodySizeHistories;
    }

    public BodySize setBodySizeHistories(List<BodySizeHistory> bodySizeHistories) {
        this.bodySizeHistories = bodySizeHistories;
        return this;
    }
}
