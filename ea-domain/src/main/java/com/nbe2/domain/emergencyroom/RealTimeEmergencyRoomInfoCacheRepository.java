package com.nbe2.domain.emergencyroom;

import java.util.Optional;

public interface RealTimeEmergencyRoomInfoCacheRepository {

    void cache(RealTimeEmergencyRoomInfo info);

    Optional<RealTimeEmergencyRoomInfo> findById(String hpId);
}
