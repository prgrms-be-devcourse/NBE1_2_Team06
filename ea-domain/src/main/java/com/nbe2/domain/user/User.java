package com.nbe2.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users", indexes = @Index(name = "idx_email", columnList = "email"))
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Enumerated(value = EnumType.STRING)
    private SignupStatus signupStatus;

    @Builder
    public User(
            String name, String email, String password, UserRole role, SignupStatus signupStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.signupStatus = signupStatus;
    }

    public static User createNormalUserOf(String name, String email, String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password) // Spring Security 설정되면 PasswordEncoder 적용
                .role(UserRole.USER)
                .signupStatus(SignupStatus.APPROVED)
                .build();
    }

    public void approve() {
        this.signupStatus = SignupStatus.APPROVED;
    }
}
