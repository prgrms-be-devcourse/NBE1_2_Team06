package com.nbe2.domain.review;

import jakarta.persistence.Column;
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
@Table(name = "reviews")
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emergency_room_id")
    private EmergencyRoom emergencyRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Double speedScore;

    @Column(nullable = false)
    private Double kindScore;

    @Column(nullable = false)
    private Double facilityScore;

    public static Review from(ReviewInfo reviewInfo, User user, EmergencyRoom emergencyRoom) {
        return Review.builder()
                .emergencyRoom(emergencyRoom)
                .user(user)
                .content(reviewInfo.content())
                .speedScore(reviewInfo.speedScore())
                .kindScore(reviewInfo.kindScore())
                .facilityScore(reviewInfo.facilityScore())
                .build();
    }

    public void updateReview(ReviewUpdateInfo reviewUpdateInfo) {
        this.content = reviewUpdateInfo.content();
        this.speedScore = reviewUpdateInfo.speedScore();
        this.kindScore = reviewUpdateInfo.kindScore();
        this.facilityScore = reviewUpdateInfo.facilityScore();
    }
}
