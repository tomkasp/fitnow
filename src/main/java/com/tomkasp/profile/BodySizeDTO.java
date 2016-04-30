package com.tomkasp.profile;

/**
 * @author Tomasz Kasprzycki
 */
public class BodySizeDTO {

    private Long id;
    private Integer waist;
    private Integer thigh;
    private Integer chest;
    private Integer hip;
    private Integer arm;
    private Integer neck;

    public Integer getArm() {
        return arm;
    }

    public BodySizeDTO setArm(Integer arm) {
        this.arm = arm;
        return this;
    }

    public Integer getChest() {
        return chest;
    }

    public BodySizeDTO setChest(Integer chest) {
        this.chest = chest;
        return this;
    }

    public Integer getHip() {
        return hip;
    }

    public BodySizeDTO setHip(Integer hip) {
        this.hip = hip;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BodySizeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNeck() {
        return neck;
    }

    public BodySizeDTO setNeck(Integer neck) {
        this.neck = neck;
        return this;
    }

    public Integer getThigh() {
        return thigh;
    }

    public BodySizeDTO setThigh(Integer thigh) {
        this.thigh = thigh;
        return this;
    }

    public Integer getWaist() {
        return waist;
    }

    public BodySizeDTO setWaist(Integer waist) {
        this.waist = waist;
        return this;
    }

    @Override
    public String toString() {
        return "BodySizeDTO{" +
            "arm=" + arm +
            ", id=" + id +
            ", waist=" + waist +
            ", thigh=" + thigh +
            ", chest=" + chest +
            ", hip=" + hip +
            ", neck=" + neck +
            '}';
    }
}
