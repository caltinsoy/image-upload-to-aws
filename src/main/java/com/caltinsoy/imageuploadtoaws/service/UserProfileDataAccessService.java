package com.caltinsoy.imageuploadtoaws.service;

import com.caltinsoy.imageuploadtoaws.model.UserProfile;
import com.caltinsoy.imageuploadtoaws.repository.FakeUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserProfileDataAccessService { // we access our in mem db via this class !
    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    @Autowired
    public UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    List<UserProfile> getUserProfiles(){
        return fakeUserProfileDataStore.getUserProfiles();
    }

    UserProfile getUserProfile(UUID uuid){
        return this.fakeUserProfileDataStore.getUserProfile(uuid);
    }

}
