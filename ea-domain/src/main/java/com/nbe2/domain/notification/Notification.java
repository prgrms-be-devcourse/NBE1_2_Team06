package com.nbe2.domain.notification;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;

@Getter
@Entity
@Table(name = "notifications", indexes = @Index(name = "idx_owner_id", columnList = "owner_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    private Notification(Long ownerId, String title, NotificationType type) {
        this.ownerId = ownerId;
        this.title = title;
        this.type = type;
    }

    public static Notification of(Long ownerId, String title, NotificationType type) {
        return new Notification(ownerId, title, type);
    }
}
