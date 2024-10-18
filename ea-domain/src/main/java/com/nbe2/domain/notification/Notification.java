package com.nbe2.domain.notification;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;
import com.nbe2.domain.user.User;

@Getter
@Entity
@Table(name = "notifications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private User target;

    @Column(nullable = false)
    private String referenceUri;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    private boolean isRead;

    @Builder
    private Notification(User target, String referenceUri, String title, NotificationType type) {
        this.target = target;
        this.referenceUri = referenceUri;
        this.title = title;
        this.type = type;
        this.isRead = false;
    }

    public static Notification of(
            User target, String referenceUri, String title, NotificationType type) {
        return Notification.builder()
                .target(target)
                .referenceUri(referenceUri)
                .title(title)
                .type(type)
                .build();
    }
}
