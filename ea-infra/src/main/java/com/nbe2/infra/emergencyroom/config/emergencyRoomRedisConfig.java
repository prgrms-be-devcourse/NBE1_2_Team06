package com.nbe2.infra.emergencyroom.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomInfo;

@Configuration
@RequiredArgsConstructor
public class EmergencyRoomRedisConfig {

    public static final Duration REAL_TIME_INFO_TTL = Duration.ofMinutes(3);

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<String, RealTimeEmergencyRoomInfo> realTimeEmergencyInfoRedisTemplate() {
        RedisTemplate<String, RealTimeEmergencyRoomInfo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(
                new Jackson2JsonRedisSerializer<>(RealTimeEmergencyRoomInfo.class));
        return redisTemplate;
    }
}
