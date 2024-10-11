package com.nbe2.domain.emergencyroom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nbe2.domain.emergencyroom.exception.EmergencyRoomNotFoundException;

@ExtendWith(MockitoExtension.class)
class EmergencyRoomReaderTest {

    @InjectMocks private EmergencyRoomReader emergencyRoomReader;

    @Mock private EmergencyRoomRepository emergencyRoomRepository;

    @Nested
    @DisplayName("ID로 응급실을 조회한다.")
    class ReadByIdTest {

        @Test
        @DisplayName("유효한 ID 전달 시 응급실을 반환한다.")
        void givenEmergencyRoomId_whenEmergencyRoomExists_thenShouldReturnEmergencyRoom() {
            // given
            Long emergencyRoomId = 1L;
            EmergencyRoom expected = EmergencyRoomFixture.create();

            // when
            when(emergencyRoomRepository.findById(emergencyRoomId))
                    .thenReturn(Optional.of(expected));

            // then
            EmergencyRoom actual = emergencyRoomReader.read(emergencyRoomId);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("존재하지 않는 응급실 조회 시 예외가 발생한다.")
        void givenEmergencyRoomId_whenEmergencyRoomNotExists_thenShouldThrowException() {
            // given
            Long emergencyRoomId = 1L;

            // when
            when(emergencyRoomRepository.findById(emergencyRoomId)).thenReturn(Optional.empty());

            // then
            assertThrows(
                    EmergencyRoomNotFoundException.class,
                    () -> emergencyRoomReader.read(emergencyRoomId));
        }
    }

    @Nested
    @DisplayName("병원 ID로 응급실을 조회한다.")
    class ReadByHospitalIdTest {

        @Test
        @DisplayName("유효한 병원 ID 전달 시 응급실을 반환한다.")
        void givenHospitalId_whenEmergencyRoomExists_thenShouldReturnEmergencyRoom() {
            // given
            String hospitalId = EmergencyRoomFixture.HP_ID;
            EmergencyRoom expected = EmergencyRoomFixture.create();

            // when
            when(emergencyRoomRepository.findByHpId(hospitalId)).thenReturn(Optional.of(expected));

            // then
            EmergencyRoom actual = emergencyRoomReader.read(hospitalId);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("존재하지 않는 병원 ID로 조회 시 예외가 발생한다.")
        void givenHospitalId_whenEmergencyRoomNotExists_thenShouldThrowException() {
            // given
            String hospitalId = "HP999";

            // when
            when(emergencyRoomRepository.findByHpId(hospitalId)).thenReturn(Optional.empty());

            // then
            assertThrows(
                    EmergencyRoomNotFoundException.class,
                    () -> emergencyRoomReader.read(hospitalId));
        }
    }

    @Nested
    @DisplayName("병원 이름으로 응급실 ID 목록을 조회한다.")
    class ReadByHospitalNameTest {

        @Test
        @DisplayName("병원 이름에 해당하는 응급실 ID 목록을 반환한다.")
        void givenHospitalName_whenEmergencyRoomsExist_thenShouldReturnListOfHpIds() {
            // given
            String hospitalName = EmergencyRoomFixture.HOSPITAL_NAME;
            List<String> expected = List.of(EmergencyRoomFixture.HP_ID, "HP002");

            // when
            when(emergencyRoomRepository.findByHospitalNameContaining(hospitalName))
                    .thenReturn(Collections.nCopies(2, EmergencyRoomFixture.create()));

            // then
            List<String> actual = emergencyRoomReader.readByHospitalName(hospitalName);
            assertEquals(expected.size(), actual.size());
        }

        @Test
        @DisplayName("해당 이름의 병원이 없을 경우 빈 목록을 반환한다.")
        void givenHospitalName_whenNoEmergencyRoomsExist_thenShouldReturnEmptyList() {
            // given
            String hospitalName = "없는병원";

            // when
            when(emergencyRoomRepository.findByHospitalNameContaining(hospitalName))
                    .thenReturn(Collections.emptyList());

            // then
            List<String> actual = emergencyRoomReader.readByHospitalName(hospitalName);
            assertTrue(actual.isEmpty());
        }
    }

    @Nested
    @DisplayName("병원 이름으로 좌표를 조회한다.")
    class FindByHospitalNameTest {

        @Test
        @DisplayName("유효한 병원 이름 전달 시 좌표를 반환한다.")
        void givenHospitalName_whenEmergencyRoomExists_thenShouldReturnCoordinates() {
            // given
            String hospitalName = EmergencyRoomFixture.HOSPITAL_NAME;
            Coordinate expected = EmergencyRoomFixture.COORDINATE;

            // when
            when(emergencyRoomRepository.findByHospitalName(hospitalName))
                    .thenReturn(Optional.of(EmergencyRoomFixture.create()));

            // then
            Coordinate actual = emergencyRoomReader.readCoordinate(hospitalName);
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("좌표와 거리로 응급실 정보를 조회한다.")
    class ReadByCoordinateAndDistanceTest {

        @Test
        @DisplayName("유효한 좌표와 거리 전달 시 응급실 목록을 반환한다.")
        void
                givenCoordinateAndDistance_whenEmergencyRoomsExist_thenShouldReturnListOfEmergencyRooms() {
            // given
            Coordinate coordinate = EmergencyRoomFixture.COORDINATE;
            double distance = EmergencyRoomFixture.DISTANCE;
            List<EmergencyRoomMapInfo> expected = List.of(EmergencyRoomFixture.createMapInfo());

            // when
            when(emergencyRoomRepository.findByCoordinateAndDistance(
                            any(Coordinate.class), anyDouble()))
                    .thenReturn(expected);

            // then
            List<EmergencyRoomMapInfo> actual = emergencyRoomReader.read(coordinate, distance);
            assertEquals(expected.size(), actual.size());
        }

        @Test
        @DisplayName("해당 좌표와 거리 내에 응급실이 없을 경우 빈 목록을 반환한다.")
        void givenCoordinateAndDistance_whenNoEmergencyRoomsExist_thenShouldReturnEmptyList() {
            // given
            Coordinate coordinate = EmergencyRoomFixture.COORDINATE;
            double distance = EmergencyRoomFixture.DISTANCE;

            // when
            when(emergencyRoomRepository.findByCoordinateAndDistance(
                            any(Coordinate.class), anyDouble()))
                    .thenReturn(Collections.emptyList());

            // then
            List<EmergencyRoomMapInfo> actual = emergencyRoomReader.read(coordinate, distance);
            assertTrue(actual.isEmpty());
        }
    }
}
