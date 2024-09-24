package com.nbe2.domain.emergencyroom;

import java.util.List;

public interface EmergencyRoomClient {

    List<RealTimeEmergencyInfo> getRealTimeEmergencyData(String region, String subRegion);
}
