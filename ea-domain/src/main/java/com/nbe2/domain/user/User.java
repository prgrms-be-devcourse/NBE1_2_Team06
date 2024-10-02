package com.nbe2.domain.user;

import jakarta.persistence.*;

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
    private ApprovalStatus approvalStatus;

    @OneToOne(
            optional = false,
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private MedicalPersonInfo medicalPersonInfo;

    @Builder
    public User(
            String name,
            String email,
            String password,
            UserRole role,
            ApprovalStatus approvalStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.approvalStatus = approvalStatus;
    }

    public static User of(String name, String email, String password) {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(UserRole.USER)
                .approvalStatus(ApprovalStatus.APPROVED)
                .build();
    }

    public void assignMedicalRole(MedicalPersonInfo medicalPersonInfo) {
        this.medicalPersonInfo = medicalPersonInfo;
        this.role = UserRole.MEDICAL_PERSON;
        this.approvalStatus = ApprovalStatus.PENDING;
    }

    public void approve() {
        this.approvalStatus = ApprovalStatus.APPROVED;
    }
}
