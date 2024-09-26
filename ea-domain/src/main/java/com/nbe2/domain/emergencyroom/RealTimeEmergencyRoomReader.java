package com.nbe2.domain.emergencyroom;

import java.util.List;

public interface RealTimeEmergencyRoomReader {

    List<RealTimeEmergencyInfo> getRealTimeEmergencyData(Region region);
}
