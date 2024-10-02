package com.nbe2.domain.user;

import static com.nbe2.domain.global.TestConstants.*;

import java.lang.reflect.Field;

public class UserFixture {

    public static User createUser() {
        return User.of(NAME, EMAIL, PASSWORD);
    }

    public static User createPendingUser() {
        return User.builder()
                .name(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(UserRole.MEDICAL_PERSON)
                .approvalStatus(ApprovalStatus.PENDING)
                .build();
    }

    public static User createUserWithId() {
        User user = User.of(NAME, EMAIL, PASSWORD);

        try {
            Field field = User.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(user, ID);
        } catch (Exception ignored) {
        }

        return user;
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
