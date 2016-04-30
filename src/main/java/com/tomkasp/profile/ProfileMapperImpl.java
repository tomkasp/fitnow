package com.tomkasp.profile;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@Service
public class ProfileMapperImpl implements ProfileMapper {


    Map<Goal, ProfileOutDTO.Goal> goalMap = new HashMap<>();
    Map<DailyActivity, ProfileOutDTO.DailyActivity> dailyActivityMap = new HashMap<>();

    @PostConstruct
    public void init() {
        goalMap.put(Goal.LOOSE, new ProfileOutDTO.Goal().setId("0").setName("Loose weight"));
        goalMap.put(Goal.GAIN, new ProfileOutDTO.Goal().setId("1").setName("Gain muscles"));

        dailyActivityMap.put(DailyActivity.SMALL, new ProfileOutDTO.DailyActivity().setId("0").setName("Small"));
        dailyActivityMap.put(DailyActivity.MEDIUM, new ProfileOutDTO.DailyActivity().setId("1").setName("Medium"));
        dailyActivityMap.put(DailyActivity.HIGH, new ProfileOutDTO.DailyActivity().setId("2").setName("High"));
        dailyActivityMap.put(DailyActivity.VERY_HIGH, new ProfileOutDTO.DailyActivity().setId("3").setName("Very High"));
    }

    @Override
    public ProfileOutDTO profileToProfileOutDTO(Profile profile) {
        ProfileOutDTO profileOutDTO = new ProfileOutDTO();
        profileOutDTO.setId(profile.getId())
            .setAge(profile.getAge())
            .setWeight(profile.getBodyWeight())
            .setHeight(profile.getHeight())
            .setGoal(convertGoal(profile.getGoal()))
            .setDailyActivity(convertDailyActivity(profile.getDailyActivity()))
            .setSex(convertSex(profile.getSex()))
            .setWeightChangeQuantity(profile.getWeightDifference());
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

    private ProfileOutDTO.Goal convertGoal(Goal goal) {
        return goalMap.get(goal);
    }

    private ProfileOutDTO.DailyActivity convertDailyActivity(DailyActivity dailyActivity) {
        return dailyActivityMap.get(dailyActivity);
    }

    private String convertSex(Sex sex) {
        if(sex.equals(Sex.MALE)){
            return "0";
        }
        return "1";
    }
}
