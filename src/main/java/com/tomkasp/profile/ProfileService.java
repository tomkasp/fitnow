package com.tomkasp.profile;

import com.tomkasp.repository.ProfileRepository;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Transactional(readOnly = true)
public class ProfileService {

    private final Logger log = LoggerFactory.getLogger(ProfileService.class);

    private final ProfileRepository profileRepository;
    private final ProfileMapperImpl profileMapperImpl;
    private final UserService userService;
    private final ConnectionRepository connectionRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, ProfileMapperImpl profileMapperImpl, UserService userService, ConnectionRepository connectionRepository) {
        this.profileRepository = profileRepository;
        this.profileMapperImpl = profileMapperImpl;
        this.userService = userService;
        this.connectionRepository = connectionRepository;
    }

    public ProfileOutDTO findMine() {
        log.debug("Request to find logged user Profile");
        final Long id = userService.getUserWithAuthorities().getId();
        return profileRepository.findByUserId(id)
            .map(result -> {
                    final String facebookImageUrl = getFacebookImageUrl();
                    ProfileOutDTO profileOutDTO = profileMapperImpl.profileToProfileOutDTO(result);
                    profileOutDTO.setSocialProfileImgUrl(facebookImageUrl);
                    return profileOutDTO;
                }
            ).orElse(profileMapperImpl.emptyProfileOutDTO());
    }

    public ProfileOutDTO findOne(Long id) {
        log.debug("Request to find Profile : {}", id);
        Profile profile = profileRepository.findOne(id);
        return Optional.ofNullable(profile).map(profileMapperImpl::profileToProfileOutDTO
        ).orElse(null);
    }


    public List<ProfileOutDTO> findAll() {
        log.debug("Request to get all Profiles");
        List<ProfileOutDTO> result = profileRepository.findAll().stream()
            .map(profileMapperImpl::profileToProfileOutDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    @Transactional(readOnly = false)
    public ProfileOutDTO save(ProfileInDTO profileInDTO) {
        log.debug("Request to save Profile : {}", profileInDTO);
        Profile profile = profileMapperImpl.profileInDTOToProfile(profileInDTO);
        //TODO handle situation when user is not loged
        profile.setUser(userService.getUserWithAuthorities());
        profile = profileRepository.save(profile);
        ProfileOutDTO result = profileMapperImpl.profileToProfileOutDTO(profile);
        return result;
    }

    public void delete(Long id) {
        log.debug("Request to delete Profile : {}", id);
        profileRepository.delete(id);
    }

    private String getFacebookImageUrl(){
        final String login = userService.getUserWithAuthorities().getLogin();
        final Connection<Facebook> connection = connectionRepository.getConnection(Facebook.class, login);
        return Optional.ofNullable(connection)
            .map(result -> result.getImageUrl())
            .orElse("");
    }
}
