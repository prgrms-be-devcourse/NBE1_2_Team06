package com.nbe2.domain.notice;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emergency_room_id")
    private EmergencyRoom emergencyRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    public static Notice from(NoticeDomain domain) {
        return Notice.builder()
                .noticeId(domain.getNoticeId())
                .user(User.from(domain.getUserId()))
                .emergencyRoom(EmergencyRoom.from(domain.getEmergencyRoomId()))
                .title(domain.getNoticeInfo().title())
                .content(domain.getNoticeInfo().content())
                .build();
    }

    public static Notice from(Long noticeId) {
        return Notice.builder().noticeId(noticeId).build();
    }

    public NoticeDomain toDomain() {
        return new NoticeDomain(
                noticeId, user.getId(), emergencyRoom.getId(), new NoticeInfo(title, content));
    }
}
