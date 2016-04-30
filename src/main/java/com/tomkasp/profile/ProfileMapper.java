package com.tomkasp.profile;

import org.mapstruct.Mapper;

/**
 * @author Tomasz Kasprzycki
 */
public interface ProfileMapper {

    ProfileOutDTO profileToProfileOutDTO(Profile profile);

    Profile profileInDTOToProfile(ProfileInDTO profileInDTO);
}
