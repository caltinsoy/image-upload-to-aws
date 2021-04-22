package com.caltinsoy.imageuploadtoaws.model;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

    private final UUID userId;
    private final String userName;
    private String userImageLink;

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Optional<String> getUserImageLink() {
        return Optional.ofNullable(userImageLink);
    }

    public void setUserImageLink(String userImageLink) {
        this.userImageLink = userImageLink;
    }

    public UserProfile(UUID userId, String userName, String userImageLink) {
        this.userId = userId;
        this.userName = userName;
        this.userImageLink = userImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getUserImageLink(), that.getUserImageLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getUserImageLink());
    }
}
