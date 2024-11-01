package com.nbe2.domain.emergencyroom;

import static com.nbe2.domain.global.TestConstants.ID;

import java.lang.reflect.Field;
import java.util.List;

public class EmergencyRoomFixture {

    public static final String HP_ID = "HP001";
    public static final String HOSPITAL_NAME = "서울종합병원";
    public static final String ZIP_CODE = "12345";
    public static final String ADDRESS = "서울특별시 중구 남대문로 123";
    public static final String MAIN_CONTACT_NUMBER = "02-123-4567";
    public static final String EMERGENCY_ROOM_CONTACT_NUMBER = "02-987-6543";
    public static final String SIMPLE_MAP = "서울종합병원 위치";
    public static final boolean EMERGENCY_ROOM_AVAILABILITY = true;
    public static final Coordinate COORDINATE = Coordinate.of(126.9784, 37.5665);
    public static final String MEDICAL_DEPARTMENTS = "응급의학과, 흉부외과, 신경외과";
    public static final int TOTAL_BED_COUNT = 100;
    public static final int THORACIC_ICU_BED_COUNT = 10;
    public static final int NEUROLOGICAL_ICU_BED_COUNT = 15;
    public static final int EMERGENCY_ROOM_BED_COUNT = 20;
    public static final int GENERAL_WARD_BED_COUNT = 30;
    public static final int GENERAL_ICU_BED_COUNT = 10;
    public static final int NEONATAL_ICU_BED_COUNT = 5;
    public static final int OPERATING_ROOM_BED_COUNT = 10;
    public static final double DISTANCE = 5.0;
    public static final String EMERGENCY_PHONE = "02-987-6543";
    public static final String INPUT_DATE = "2024-10-10 12:00";

    public static EmergencyRoom create() {
        return EmergencyRoom.builder()
                .hpId(HP_ID)
                .hospitalName(HOSPITAL_NAME)
                .zipCode(ZIP_CODE)
                .address(ADDRESS)
                .mainContactNumber(MAIN_CONTACT_NUMBER)
                .emergencyRoomContactNumber(EMERGENCY_ROOM_CONTACT_NUMBER)
                .simpleMap(SIMPLE_MAP)
                .emergencyRoomAvailability(EMERGENCY_ROOM_AVAILABILITY)
                .coordinate(COORDINATE)
                .medicalDepartments(MEDICAL_DEPARTMENTS)
                .totalBedCount(TOTAL_BED_COUNT)
                .thoracicIcuBedCount(THORACIC_ICU_BED_COUNT)
                .neurologicalIcuBedCount(NEUROLOGICAL_ICU_BED_COUNT)
                .emergencyRoomBedCount(EMERGENCY_ROOM_BED_COUNT)
                .generalWardBedCount(GENERAL_WARD_BED_COUNT)
                .generalIcuBedCount(GENERAL_ICU_BED_COUNT)
                .neonatalIcuBedCount(NEONATAL_ICU_BED_COUNT)
                .operatingRoomBedCount(OPERATING_ROOM_BED_COUNT)
                .build();
    }

    public static EmergencyRoom createWithId() {
        EmergencyRoom emergencyRoom = create();

        try {
            Field field = EmergencyRoom.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(emergencyRoom, ID);
        } catch (Exception ignored) {
        }

        return emergencyRoom;
    }

    public static EmergencyRoomMapInfo createMapInfo() {
        return new EmergencyRoomMapInfo(
                1L, HP_ID, HOSPITAL_NAME, ADDRESS, SIMPLE_MAP, COORDINATE, DISTANCE);
    }

    public static RealTimeEmergencyRoomInfo createRealTimeInfo() {
        return new RealTimeEmergencyRoomInfo(
                HP_ID,
                HOSPITAL_NAME,
                EMERGENCY_PHONE,
                INPUT_DATE,
                10, // availableBeds
                2, // operatingRoomBeds
                1, // neuroIcuBeds
                0, // neonatalIcuBeds
                1, // chestIcuBeds
                3, // generalIcuBeds
                5, // generalWardBeds
                true, // isCtAvailable
                true, // isMriAvailable
                false, // isAngiographyAvailable
                true, // isVentilatorAvailable
                false, // isIncubatorAvailable
                true // isAmbulanceAvailable
                );
    }

    public static List<RealTimeEmergencyRoomInfo> createRealTimeInfoList() {
        return List.of(createRealTimeInfo(), createRealTimeInfo());
    }

    public static Region getRegion() {
        return new Region("서울특별시", "중구");
    }
}
