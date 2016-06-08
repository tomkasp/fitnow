package com.tomkasp.profile;

import com.tomkasp.profile.domain.Profile;
import com.tomkasp.profile.dto.ProfileInDTO;
import com.tomkasp.profile.dto.ProfileOutDTO;

/**
 * @author Tomasz Kasprzycki
 */
public interface ProfileMapper {

    ProfileOutDTO profileToProfileOutDTO(Profile profile);

    Profile profileInDTOToProfile(ProfileInDTO profileInDTO);
}
