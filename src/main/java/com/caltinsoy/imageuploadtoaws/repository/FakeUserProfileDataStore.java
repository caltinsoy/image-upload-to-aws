package com.caltinsoy.imageuploadtoaws.repository;

import com.caltinsoy.imageuploadtoaws.model.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore { //in mem db

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("05b223ad-4ea4-4734-acdb-dc4dc01cde00"), "Marcus", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("45ba55ee-0587-489f-9054-fb8f237f32c3"), "Startrex", null));
    }
    //when you restart your server you will lost your images on browser , because userImageLınk is null !

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

    public UserProfile getUserProfile(UUID uuid) {
        for (UserProfile userProfile : USER_PROFILES) {
            if (userProfile.getUserId().equals(uuid)) {
                return userProfile;
            }
        }
        return null;
    }
}
