package com.nbe2.domain.emergencyroom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.global.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
public class EmergencyRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hospitalName;

    private String zipCode;

    private String address;

    private String mainContactNumber;

    private String emergencyRoomContactNumber;

    private String simpleMap;

    private boolean emergencyRoomAvailability;

    private String longitude;

    private String latitude;

    private String medicalDepartments;

    private int totalBedCount;

    private int thoracicIcuBedCount;

    private int neurologicalIcuBedCount;

    private int emergencyRoomBedCount;

    private int generalWardBedCount;

    private int generalIcuBedCount;

    private int neonatalIcuBedCount;

    private int operatingRoomBedCount;
}
