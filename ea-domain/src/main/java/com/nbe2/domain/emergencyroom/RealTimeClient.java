package com.nbe2.domain.emergencyroom;

import java.util.List;

public interface RealTimeClient {

    List<RealTimeEmergencyRoomInfo> getRealTimeInfo(Region region);
}
