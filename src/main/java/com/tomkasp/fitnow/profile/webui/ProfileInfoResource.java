package com.tomkasp.fitnow.profile.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.profile.dto.ProfileInfoOutDTO;
import com.tomkasp.fitnow.profile.application.ProfileInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api")
public class ProfileInfoResource {

    private final ProfileInfoService profileInfoService;
    private final Logger log = LoggerFactory.getLogger(ProfileInfoResource.class);

    @Autowired
    public ProfileInfoResource(ProfileInfoService profileInfoService) {
        this.profileInfoService = profileInfoService;
    }

    @RequestMapping(value = "/profileinfo",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ProfileInfoOutDTO getProfileInfo() {
        log.debug("Rest request to get the profile info");
        return profileInfoService.fetchProfileInfoService();
    }
}
