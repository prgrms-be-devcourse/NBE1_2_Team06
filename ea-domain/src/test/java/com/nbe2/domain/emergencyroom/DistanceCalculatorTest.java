package com.nbe2.domain.emergencyroom;

import static com.nbe2.domain.emergencyroom.EmergencyRoomFixture.COORDINATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DistanceCalculatorTest {

    @InjectMocks private DistanceCalculator distanceCalculator;

    @Mock private EmergencyRoomReader emergencyRoomReader;

    @Nested
    @DisplayName("거리를 계산한다")
    class CalculateDistanceTest {

        @Test
        @DisplayName("실시간 응급실 정보를 전달하면 현재 위치로부터 거리를 계산하고 정렬한다")
        void givenRealTimeEmergencyRoomInfos_whenCalculate_thenReturnSortedByDistance() {
            // given
            List<RealTimeEmergencyRoomInfo> realTimeEmergencyRoomInfos =
                    EmergencyRoomFixture.createRealTimeInfoList();

            EmergencyRoom emergencyRoom1 = EmergencyRoomFixture.create();
            EmergencyRoom emergencyRoom2 = EmergencyRoomFixture.create();

            when(emergencyRoomReader.read(realTimeEmergencyRoomInfos.get(0).hospitalId()))
                    .thenReturn(emergencyRoom1);
            when(emergencyRoomReader.read(realTimeEmergencyRoomInfos.get(1).hospitalId()))
                    .thenReturn(emergencyRoom2);

            // when
            List<RealTimeEmergencyRoomWithDistance> result =
                    distanceCalculator.calculate(realTimeEmergencyRoomInfos, COORDINATE);

            // then
            assertEquals(2, result.size());
            assertEquals(result.get(0).distance(), 0.0);
            assertTrue(result.get(0).distance() <= result.get(1).distance()); // 정렬된 결과인지 확인
        }

        @Test
        @DisplayName("빈 리스트를 전달하면 빈 결과를 반환한다")
        void givenEmptyList_whenCalculate_thenReturnEmptyList() {
            // given
            List<RealTimeEmergencyRoomInfo> realTimeEmergencyRoomInfos = List.of();
            Coordinate currentCoordinate = Coordinate.of(126.9784, 37.5665);

            // when
            List<RealTimeEmergencyRoomWithDistance> result =
                    distanceCalculator.calculate(realTimeEmergencyRoomInfos, currentCoordinate);

            // then
            assertTrue(result.isEmpty());
        }
    }
}
