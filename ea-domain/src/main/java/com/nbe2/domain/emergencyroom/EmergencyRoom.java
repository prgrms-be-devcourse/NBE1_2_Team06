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
            String hpid,
            String hospitalName,
            String zipCode,
            String address,
            String mainContactNumber,
            String emergencyRoomContactNumber,
            String simpleMap,
            boolean emergencyRoomAvailability,
            double longitude,
            double latitude,
            String medicalDepartments,
            int totalBedCount,
            int thoracicIcuBedCount,
            int neurologicalIcuBedCount,
            int emergencyRoomBedCount,
            int generalWardBedCount,
            int generalIcuBedCount,
            int neonatalIcuBedCount,
            int operatingRoomBedCount) {
        this.hpid = hpid;
        this.hospitalName = hospitalName;
        this.zipCode = zipCode;
        this.address = address;
        this.mainContactNumber = mainContactNumber;
        this.emergencyRoomContactNumber = emergencyRoomContactNumber;
        this.simpleMap = simpleMap;
        this.emergencyRoomAvailability = emergencyRoomAvailability;
        this.medicalDepartments = medicalDepartments;
        this.location =
                Location.builder()
                        .longitude(String.valueOf(longitude))
                        .latitude(String.valueOf(longitude))
                        .build();
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

    public EmergencyRoomInfo toEmergencyRoomInfo() {
        return EmergencyRoomInfo.builder()
                .id(hpid) // Assuming 'id' corresponds to 'hospitalId'
                .hospitalName(hospitalName)
                .zipCode(zipCode)
                .address(address)
                .mainContactNumber(mainContactNumber)
                .emergencyRoomContactNumber(emergencyRoomContactNumber)
                .simpleMap(simpleMap)
                .emergencyRoomAvailability(emergencyRoomAvailability)
                .longitude(location.longitude)
                .latitude(location.latitude)
                .medicalDepartments(medicalDepartments)
                .totalBedCount(bedCount.totalBedCount)
                .thoracicIcuBedCount(bedCount.thoracicIcuBedCount)
                .neurologicalIcuBedCount(bedCount.neurologicalIcuBedCount)
                .emergencyRoomBedCount(bedCount.emergencyRoomBedCount)
                .generalWardBedCount(bedCount.generalWardBedCount)
                .generalIcuBedCount(bedCount.generalIcuBedCount)
                .neonatalIcuBedCount(bedCount.neonatalIcuBedCount)
                .operatingRoomBedCount(bedCount.operatingRoomBedCount)
                .build();
    }
}
