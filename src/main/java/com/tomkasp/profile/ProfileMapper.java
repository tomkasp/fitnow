package com.tomkasp.profile;

import org.mapstruct.Mapper;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfileMapper {

    ProfileOutDTO profileToProfileOutDTO(Profile profile);

    Profile profileInDTOToProfile(ProfileInDTO profileInDTO);
}
