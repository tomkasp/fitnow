package com.tomkasp.profile;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
public class ProfileInfoOutDTO implements Serializable {

    private static final long serialVersionUID = 1897479787985921044L;

    String facebookImgUrl;

    public String getFacebookImgUrl() {
        return facebookImgUrl;
    }

    public ProfileInfoOutDTO setFacebookImgUrl(String facebookImgUrl) {
        this.facebookImgUrl = facebookImgUrl;
        return this;
    }
}
