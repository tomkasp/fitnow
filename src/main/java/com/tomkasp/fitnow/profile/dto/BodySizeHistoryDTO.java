package com.tomkasp.fitnow.profile.dto;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
public class BodySizeHistoryDTO implements Serializable{

    private static final long serialVersionUID = -4125318632614983572L;

    private Long id;
    private Integer waist;
    private Integer thigh;
    private Integer chest;
    private Integer hip;
    private Integer arm;
    private Integer neck;
    private LocalDate date;

    public BodySizeHistoryDTO setArm(Integer arm) {
        this.arm = arm;
        return this;
    }

    public BodySizeHistoryDTO setChest(Integer chest) {
        this.chest = chest;
        return this;
    }

    public BodySizeHistoryDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BodySizeHistoryDTO setHip(Integer hip) {
        this.hip = hip;
        return this;
    }

    public BodySizeHistoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BodySizeHistoryDTO setNeck(Integer neck) {
        this.neck = neck;
        return this;
    }

    public BodySizeHistoryDTO setThigh(Integer thigh) {
        this.thigh = thigh;
        return this;
    }

    public BodySizeHistoryDTO setWaist(Integer waist) {
        this.waist = waist;
        return this;
    }
}
