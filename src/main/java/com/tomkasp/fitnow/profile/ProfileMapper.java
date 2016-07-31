package com.tomkasp.fitnow.profile;

import com.tomkasp.fitnow.profile.domain.Profile;
import com.tomkasp.fitnow.profile.dto.ProfileInDTO;
import com.tomkasp.fitnow.profile.dto.ProfileOutDTO;

/**
 * @author Tomasz Kasprzycki
 */
public interface ProfileMapper {

    ProfileOutDTO profileToProfileOutDTO(Profile profile);

    Profile profileInDTOToProfile(ProfileInDTO profileInDTO);
}
