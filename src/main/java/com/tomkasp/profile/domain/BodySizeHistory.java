package com.tomkasp.profile.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "body_size_history")
public class BodySizeHistory {

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

    @Column(name = "date", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    public Integer getArm() {
        return arm;
    }

    public BodySizeHistory setArm(Integer arm) {
        this.arm = arm;
        return this;
    }

    public Integer getChest() {
        return chest;
    }

    public BodySizeHistory setChest(Integer chest) {
        this.chest = chest;
        return this;
    }

    public DateTime getDate() {
        return date;
    }

    public BodySizeHistory setDate(DateTime date) {
        this.date = date;
        return this;
    }

    public Integer getHip() {
        return hip;
    }

    public BodySizeHistory setHip(Integer hip) {
        this.hip = hip;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BodySizeHistory setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNeck() {
        return neck;
    }

    public BodySizeHistory setNeck(Integer neck) {
        this.neck = neck;
        return this;
    }

    public Integer getThigh() {
        return thigh;
    }

    public BodySizeHistory setThigh(Integer thigh) {
        this.thigh = thigh;
        return this;
    }

    public Integer getWaist() {
        return waist;
    }

    public BodySizeHistory setWaist(Integer waist) {
        this.waist = waist;
        return this;
    }
}
