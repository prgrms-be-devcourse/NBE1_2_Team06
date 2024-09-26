package com.nbe2.domain.emergencyroom;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "emergency_rooms")
public class EmergencyRoom extends BaseTimeEntity {

    @Id
    @Column(name = "emergency_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hospitalName;

    private String zipCode;

    private String address;

    private String mainContactNumber;

    private String emergencyRoomContactNumber;

    private String simpleMap;

    private boolean emergencyRoomAvailability;

    private String medicalDepartments;

    @Embedded private Location location;

    @Embedded private BedCount bedCount;

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Location {
        private String longitude;

        private String latitude;
    }

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class BedCount {
        private int totalBedCount;

        private int thoracicIcuBedCount;

        private int neurologicalIcuBedCount;

        private int emergencyRoomBedCount;

        private int generalWardBedCount;

        private int generalIcuBedCount;

        private int neonatalIcuBedCount;

        private int operatingRoomBedCount;
    }
}
