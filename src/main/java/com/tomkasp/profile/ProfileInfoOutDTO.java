package com.tomkasp.profile;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
public class ProfileInfoOutDTO implements Serializable {

    private static final long serialVersionUID = 1897479787985921044L;

    private String facebookImgUrl;
    private String location;
    private String fullName;
    private String facebookProfileUrl;

    public String getFacebookImgUrl() {
        return facebookImgUrl;
    }

    public ProfileInfoOutDTO setFacebookImgUrl(String facebookImgUrl) {
        this.facebookImgUrl = facebookImgUrl;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ProfileInfoOutDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ProfileInfoOutDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getFacebookProfileUrl() {
        return facebookProfileUrl;
    }

    public ProfileInfoOutDTO setFacebookProfileUrl(String facebookProfileUrl) {
        this.facebookProfileUrl = facebookProfileUrl;
        return this;
    }
}
