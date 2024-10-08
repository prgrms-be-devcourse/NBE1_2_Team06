package com.nbe2.domain.notification;

import jakarta.persistence.*;

import lombok.AccessLevel;
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
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(nullable = false)
    private Long refId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    private boolean isRead;

    private Notification(User owner, Long refId, String title, NotificationType type) {
        this.owner = owner;
        this.refId = refId;
        this.title = title;
        this.type = type;
        this.isRead = false;
    }

    public static Notification of(User owner, Long refId, String title, NotificationType type) {
        return new Notification(owner, refId, title, type);
    }
}
