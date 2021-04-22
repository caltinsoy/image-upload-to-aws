package com.caltinsoy.imageuploadtoaws.service;

import com.caltinsoy.imageuploadtoaws.bucket.BucketName;
import com.caltinsoy.imageuploadtoaws.model.UserProfile;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService { //This service basically performs S3 operations !

    private final UserProfileDataAccessService userProfileDataAccessService;

    private final FileService fileService;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileService fileService) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileService = fileService;
    }

    public byte[] downloadUserProfileImage(UUID userId) {
        UserProfile userProfile = getUserProfile(userId);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName()
                , userProfile.getUserId());

        return userProfile.getUserImageLink()
                .map(key -> fileService.download(path, key))
                .orElse(new byte[0]);

    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        isFileEmpty(file);

        isFileImage(file);

        UserProfile userProfile = getUserProfile(userProfileId);

        if (userProfile != null) {
            Map<String, String> metaData = setMetaData(file);
            String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getUserId());
            String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

            try {
                fileService.save(path, fileName, Optional.of(metaData), file.getInputStream());
                userProfile.setUserImageLink(fileName);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

    }

    private UserProfile getUserProfile(UUID userProfileId) {
        return userProfileDataAccessService.getUserProfile(userProfileId);
    }


    private Map<String, String> setMetaData(MultipartFile file) {
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));
        return metaData;
    }

    private void isFileImage(MultipartFile file) {
        if (!Arrays.asList(ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_GIF.getMimeType())
                .contains(file.getContentType())) {
            throw new IllegalStateException("File must be image"); //Exception can be handle more properly
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) { //Exception can be handle more properly
            throw new IllegalStateException("File cannot be empty");
        }
    }

    public List<UserProfile> getUserProfile() {
        return userProfileDataAccessService.getUserProfiles();
    }

}
