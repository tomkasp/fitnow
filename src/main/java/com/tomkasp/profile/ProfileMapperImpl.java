package com.tomkasp.profile;

import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@Service
public class ProfileMapperImpl implements ProfileMapper {


    @Override
    public ProfileOutDTO profileToProfileOutDTO(Profile profile) {
        ProfileOutDTO profileOutDTO = new ProfileOutDTO();
        profileOutDTO.setId(profile.getId());
        return profileOutDTO;
    }

    @Override
    public Profile profileInDTOToProfile(ProfileInDTO profileInDTO) {
        Profile profile = new Profile();
        profile.setId(profileInDTO.getId())
            .setAge(profileInDTO.getAge())
            .setBodyWeight(profileInDTO.getWeight())
            .setDailyActivity(profileInDTO.getConvertedDailyActivity())
            .setHeight(profileInDTO.getHeight())
            .setWeightDifference(profileInDTO.getWeightChangeQuantity())
            .setSex(profileInDTO.getConvertedSex())
            .setGoal(profileInDTO.getConvertedGoal());
        return profile;
    }
}
