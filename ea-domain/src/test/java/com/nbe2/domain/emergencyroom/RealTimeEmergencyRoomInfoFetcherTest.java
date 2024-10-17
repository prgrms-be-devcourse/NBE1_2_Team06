package com.nbe2.domain.emergencyroom;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
class RealTimeEmergencyRoomInfoFetcherTest {

    @InjectMocks private RealTimeEmergencyRoomInfoFetcher realTimeEmergencyRoomInfoFetcher;

    @Mock private EmergencyRoomClient realTimeClient;

    @Mock private RealTimeEmergencyRoomInfoCacheManager cacheManager;

    @Mock private CoordinateToRegionConverter coordinateToRegionConverter;

    @Nested
    @DisplayName("실시간 응급실 정보를 조회한다.")
    class FetchRealTimeInfoTest {

        @Test
        @DisplayName("유효한 지역을 전달하면 실시간 응급실 정보를 반환하고 캐싱한다.")
        void givenCoordate_whenConvertRegion_FetchRealTimeInfo_thenReturnAndCacheInfo() {
            // given
            Coordinate coordinate = EmergencyRoomFixture.COORDINATE;
            List<RealTimeEmergencyRoomInfo> expectedInfo =
                    EmergencyRoomFixture.createRealTimeInfoList();
            Region expectedRegion = EmergencyRoomFixture.getRegion();

            // when
            when(coordinateToRegionConverter.convert(coordinate)).thenReturn(expectedRegion);
            when(realTimeClient.getRealTimeInfo(expectedRegion)).thenReturn(expectedInfo);

            // then
            List<RealTimeEmergencyRoomInfo> actualInfo =
                    realTimeEmergencyRoomInfoFetcher.fetch(coordinate);
            assertEquals(expectedInfo, actualInfo);
            verify(realTimeClient, times(1)).getRealTimeInfo(expectedRegion);
            verify(cacheManager, times(1)).cache(expectedInfo);
        }

        @Test
        @DisplayName("캐시 매니저가 호출되면 캐싱 로직이 동작한다.")
        void givenCoordinate_RealTimeInfo_whenConvertRegion_CacheManagerCalled_thenCacheInfo() {
            // given
            Coordinate coordinate = EmergencyRoomFixture.COORDINATE;
            Region expectedRegion = EmergencyRoomFixture.getRegion();
            List<RealTimeEmergencyRoomInfo> realTimeInfo =
                    EmergencyRoomFixture.createRealTimeInfoList();

            // when
            when(coordinateToRegionConverter.convert(coordinate)).thenReturn(expectedRegion);
            when(realTimeClient.getRealTimeInfo(expectedRegion)).thenReturn(realTimeInfo);
            doNothing().when(cacheManager).cache(realTimeInfo);

            // then
            realTimeEmergencyRoomInfoFetcher.fetch(coordinate);
            verify(cacheManager, times(1)).cache(realTimeInfo);
        }
    }
}
