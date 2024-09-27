package com.nbe2.infra.emergencyroom.cache;

import static com.nbe2.infra.emergencyroom.config.EmergencyRoomRedisConfig.REAL_TIME_INFO_TTL;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfo;
import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfoCacheRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisRealTimeEmergencyRoomInfoCacheRepository
        implements RealTimeEmergencyRoomInfoCacheRepository {

    private final RedisTemplate<String, RealTimeEmergencyRoomInfo> template;

    @Override
    public void cache(RealTimeEmergencyRoomInfo info) {
        template.opsForValue().set(getKey(info.hospitalId()), info, REAL_TIME_INFO_TTL);
    }

    @Override
    public Optional<RealTimeEmergencyRoomInfo> findById(String hpId) {
        String key = getKey(hpId);
        Optional<RealTimeEmergencyRoomInfo> realTimeInfo =
                Optional.ofNullable(template.opsForValue().get(key));
        realTimeInfo.ifPresentOrElse(
                info -> log.info("Get User from Cache - {} : {}", key, info.hospitalName()),
                () -> log.info("No User Cache - {}", key));
        return realTimeInfo;
    }

    private String getKey(String hpId) {
        return "REAL_TIME_ER:" + hpId;
    }
}
