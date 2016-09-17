package com.tomkasp.fitnow.profile.application;

import com.tomkasp.domain.User;
import com.tomkasp.fitnow.profile.dto.ProfileInfoOutDTO;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class ProfileInfoService {

    private final Logger log = LoggerFactory.getLogger(ProfileInfoService.class);

    private final ConnectionRepository connectionRepository;
    private final UserService userService;

    @Autowired
    public ProfileInfoService(ConnectionRepository connectionRepository, UserService userService) {
        this.connectionRepository = connectionRepository;
        this.userService = userService;
    }

    public ProfileInfoOutDTO fetchProfileInfoService() {
        String fullName = null;
        String imageUrl = "assets/img/b11.jpg";
        String location = "";
        String facebookProfileUrl = "";
        try {
            final User userWithAuthorities = userService.getUserWithAuthorities();
            if (userWithAuthorities.getFirstName() == null && userWithAuthorities.getLastName() == null) {
                fullName = userWithAuthorities.getLogin();
            } else {
                fullName = userWithAuthorities.getFirstName() + " " + userWithAuthorities.getLastName();
            }
            final Connection<Facebook> facebookConnection = connectionRepository.getPrimaryConnection(Facebook.class);
            location = facebookConnection.getApi().userOperations().getUserProfile().getLocation().getName();
            imageUrl = facebookConnection.getImageUrl();
            facebookProfileUrl = facebookConnection.getProfileUrl();

        } catch (NotConnectedException ex) {
            log.warn("Not assigned facebook connection");
        }
        final ProfileInfoOutDTO profileInfoOutDTO = new ProfileInfoOutDTO()
            .setFacebookImgUrl(imageUrl)
            .setLocation(location)
            .setFullName(fullName)
            .setFacebookProfileUrl(facebookProfileUrl);
        return profileInfoOutDTO;
    }
}
