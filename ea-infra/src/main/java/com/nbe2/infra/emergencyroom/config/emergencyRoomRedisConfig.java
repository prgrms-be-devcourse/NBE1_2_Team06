package com.nbe2.infra.emergencyroom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.RealTimeEmergencyInfo;

@Configuration
@RequiredArgsConstructor
public class emergencyRoomRedisConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<String, RealTimeEmergencyInfo> realTimeEmergencyInfoRedisTemplate() {
        RedisTemplate<String, RealTimeEmergencyInfo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(
                new Jackson2JsonRedisSerializer<>(RealTimeEmergencyInfo.class));
        return redisTemplate;
    }
}
