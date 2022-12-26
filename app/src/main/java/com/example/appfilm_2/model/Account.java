package com.example.appfilm_2.model;

public class Account {
    private String email;
    private String profilePicture;

    public Account(String email, String profilePicture) {
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
