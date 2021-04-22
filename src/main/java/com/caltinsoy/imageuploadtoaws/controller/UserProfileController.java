package com.caltinsoy.imageuploadtoaws.controller;

import com.caltinsoy.imageuploadtoaws.model.UserProfile;
import com.caltinsoy.imageuploadtoaws.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
public class UserProfileController { // we got an error to access from localhost:3000 to backend , so with this annotation we prevent for now ! Do not do this in production !

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfile();
    }

    @PostMapping(path = "{userId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadUserProfileImage(@PathVariable("userId") UUID userId,
                                       @RequestParam("file") MultipartFile file) throws IOException {
        userProfileService.uploadUserProfileImage(userId, file);
    }

    @GetMapping(path = "{userId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userId") UUID userId){
        return userProfileService.downloadUserProfileImage(userId);
    }
}
