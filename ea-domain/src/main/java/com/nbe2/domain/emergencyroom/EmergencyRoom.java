package com.nbe2.domain.emergencyroom;

import jakarta.persistence.Column;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "emergency_rooms")
public class EmergencyRoom extends BaseTimeEntity {

    @Id
    @Column(name = "emergency_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hpId;

    private String hospitalName;

    private String zipCode;

    private String address;

    private String mainContactNumber;

    private String emergencyRoomContactNumber;

    private String simpleMap;

    private boolean emergencyRoomAvailability;

    private String medicalDepartments;

    @Embedded private Coordinate location;

    @Embedded private BedCount bedCount;

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

    @Builder
    public EmergencyRoom(
            String hpId,
            String hospitalName,
            String zipCode,
            String address,
            String mainContactNumber,
            String emergencyRoomContactNumber,
            String simpleMap,
            boolean emergencyRoomAvailability,
            String longitude,
            String latitude,
            String medicalDepartments,
            int totalBedCount,
            int thoracicIcuBedCount,
            int neurologicalIcuBedCount,
            int emergencyRoomBedCount,
            int generalWardBedCount,
            int generalIcuBedCount,
            int neonatalIcuBedCount,
            int operatingRoomBedCount) {
        this.hpId = hpId;
        this.hospitalName = hospitalName;
        this.zipCode = zipCode;
        this.address = address;
        this.mainContactNumber = mainContactNumber;
        this.emergencyRoomContactNumber = emergencyRoomContactNumber;
        this.simpleMap = simpleMap;
        this.emergencyRoomAvailability = emergencyRoomAvailability;
        this.medicalDepartments = medicalDepartments;
        this.location = Coordinate.of(Double.valueOf(longitude), Double.valueOf(latitude));
        this.bedCount =
                BedCount.builder()
                        .totalBedCount(totalBedCount)
                        .thoracicIcuBedCount(thoracicIcuBedCount)
                        .neurologicalIcuBedCount(neurologicalIcuBedCount)
                        .emergencyRoomBedCount(emergencyRoomBedCount)
                        .generalWardBedCount(generalWardBedCount)
                        .generalIcuBedCount(generalIcuBedCount)
                        .neonatalIcuBedCount(neonatalIcuBedCount)
                        .operatingRoomBedCount(operatingRoomBedCount)
                        .build();
    }
}
