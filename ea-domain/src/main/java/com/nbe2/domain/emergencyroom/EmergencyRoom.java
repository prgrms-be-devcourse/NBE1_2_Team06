package com.nbe2.domain.emergencyroom;

import jakarta.persistence.*;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTReader;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.emergencyroom.exception.InvalidCoordinateException;
import com.nbe2.domain.global.BaseTimeEntity;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "emergency_rooms",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"hospitalName", "longitude", "latitude"})
        })
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

    @Column(columnDefinition = "GEOMETRY")
    private Point location;

    @Embedded private BedCount bedCount;

    public static Point coordinateToPoint(Coordinate coordinate) {
        String pointWKT =
                String.format("POINT(%f %f)", coordinate.getLongitude(), coordinate.getLatitude());
        try {
            return (Point) new WKTReader().read(pointWKT);
        } catch (Exception e) {
            throw InvalidCoordinateException.EXCEPTION;
        }
    }

    public Coordinate getLocation() {
        return Coordinate.of(location.getX(), location.getY());
    }

    public int getEmergencyRoomBedCount() {
        return bedCount.getEmergencyRoomBedCount();
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

        public int getEmergencyRoomBedCount() {
            return emergencyRoomBedCount;
        }
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
            Coordinate coordinate,
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
        this.location = coordinateToPoint(coordinate);
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
