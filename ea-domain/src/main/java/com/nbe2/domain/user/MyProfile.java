package com.nbe2.domain.user;

public record MyProfile(String name, String email, boolean hasMedicalAuthority) {

    public static MyProfile from(User user) {
        return new MyProfile(
                user.getName(), user.getEmail(), user.getRole().equals(UserRole.MEDICAL_PERSON));
    }
}
