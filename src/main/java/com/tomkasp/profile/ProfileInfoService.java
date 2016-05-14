package com.tomkasp.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ProfileInfoService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public ProfileInfoOutDTO fetchProfileInfoService(){
        String imageUrl = "";
        try {
         imageUrl = connectionRepository.getPrimaryConnection(Facebook.class).getImageUrl();

        }catch (NotConnectedException ex){
            log.warn("not assigned facebook conection", ex);
        }
        final ProfileInfoOutDTO profileInfoOutDTO = new ProfileInfoOutDTO().setFacebookImgUrl(imageUrl);
        return profileInfoOutDTO;
    }
}
