package com.nbe2.domain.user;

public class UserFixture {

    public static final long ID = 1L;
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public static User createUser() {
        return User.createNormalUserOf(NAME, EMAIL, PASSWORD);
    }

    public static User createPendingUser() {
        return User.builder()
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(UserRole.MEDICAL_PERSON)
                .signupStatus(SignupStatus.PENDING)
                .build();
    }

    public static UserProfile createUserProfile() {
        return new UserProfile(NAME, EMAIL, PASSWORD);
    }

    public static MedicalProfile createMedicalProfile() {
        return new MedicalProfile(ID, ID);
    }

    public static UserProfileWithLicense createUserProfileWithLicense() {
        return new UserProfileWithLicense(ID, NAME, EMAIL, ID);
    }
}
