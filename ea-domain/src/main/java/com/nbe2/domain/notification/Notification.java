package com.nbe2.domain.notification;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;
import com.nbe2.domain.user.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    private Notification(User user, String title, NotificationType type) {
        this.owner = user;
        this.title = title;
        this.type = type;
    }

    public static Notification of(User owner, String title, NotificationType type) {
        return new Notification(owner, title, type);
    }
}
