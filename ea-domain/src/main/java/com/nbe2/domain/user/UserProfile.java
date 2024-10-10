package com.nbe2.domain.user;

public record UserProfile(String name, String email, String password) {

    public static UserProfile of(String name, String email, String password) {
        return new UserProfile(name, email, password);
    }
}
