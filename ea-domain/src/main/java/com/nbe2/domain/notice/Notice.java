package com.nbe2.domain.notice;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.global.BaseTimeEntity;
import com.nbe2.domain.user.User;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notices")
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emergency_room_id")
    private EmergencyRoom emergencyRoom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private List<NoticeFile> noticeFile;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public static Notice from(NoticeInfo noticeInfo, User user, EmergencyRoom emergencyRoom) {
        return Notice.builder()
                .title((noticeInfo.title().get()))
                .content(noticeInfo.content().get())
                .emergencyRoom(emergencyRoom)
                .user(user)
                .build();
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }
}
