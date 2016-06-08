package com.tomkasp.profile.application;

import com.tomkasp.domain.User;
import com.tomkasp.profile.dto.ProfileInfoOutDTO;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

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

    public ProfileInfoOutDTO fetchProfileInfoService(){
        String imageUrl = "assets/img/b11.jpg";
        String location = "London";
        String fullName = "John Smith";
        String facebookProfileUrl = "";
        try {
            final User userWithAuthorities = userService.getUserWithAuthorities();
            final Connection<Facebook> primaryConnection = connectionRepository.getPrimaryConnection(Facebook.class);
            location = primaryConnection.getApi().userOperations().getUserProfile().getLocation().getName();
            imageUrl = primaryConnection.getImageUrl();
            facebookProfileUrl = primaryConnection.getProfileUrl();
            fullName = userWithAuthorities.getFirstName() + " " + userWithAuthorities.getLastName();
        }catch (NotConnectedException ex){
            log.warn("not assigned facebook conection", ex);
        }
        final ProfileInfoOutDTO profileInfoOutDTO = new ProfileInfoOutDTO()
            .setFacebookImgUrl(imageUrl)
            .setLocation(location)
            .setFullName(fullName)
            .setFacebookProfileUrl(facebookProfileUrl);
        return profileInfoOutDTO;
    }
}
