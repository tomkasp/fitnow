package com.tomkasp.profile;

import com.tomkasp.domain.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

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

    @Column(name = "date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    @OneToOne(optional = false) //change to manyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User user;


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

    public DateTime getDate() {
        return date;
    }

    public BodySize setDate(DateTime date) {
        this.date = date;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BodySize)) return false;
        BodySize bodySize = (BodySize) o;
        return Objects.equals(id, bodySize.id) &&
            Objects.equals(waist, bodySize.waist) &&
            Objects.equals(thigh, bodySize.thigh) &&
            Objects.equals(chest, bodySize.chest) &&
            Objects.equals(hip, bodySize.hip) &&
            Objects.equals(arm, bodySize.arm) &&
            Objects.equals(neck, bodySize.neck) &&
            Objects.equals(date, bodySize.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, waist, thigh, chest, hip, arm, neck, date);
    }
}
