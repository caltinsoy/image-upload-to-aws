package com.caltinsoy.imageuploadtoaws.bucket;

public enum BucketName {

    PROFILE_IMAGE("aws-image-upload-example");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
