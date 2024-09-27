package com.nbe2.domain.emergencyroom;

import java.util.List;

public interface EmergencyRoomClient {

    List<RealTimeEmergencyRoomInfo> getRealTimeInfo(Region region);

    List<EmergencyRoomInfo> getEmergencyRoomInfoData();
}
