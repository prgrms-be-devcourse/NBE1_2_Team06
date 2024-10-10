package com.nbe2.infra.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notification.NotificationMessage;
import com.nbe2.domain.notification.NotificationType;
import com.nbe2.infra.notification.client.CommentRedisSubscriber;

@Configuration
@RequiredArgsConstructor
public class RedisPubsubConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(
                listenerAdapter, new PatternTopic(NotificationType.COMMENT.getChannelId()));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(CommentRedisSubscriber commentRedisSubscriber) {
        return new MessageListenerAdapter(commentRedisSubscriber);
    }

    @Bean
    public RedisTemplate<String, NotificationMessage> notificationTemplate() {
        RedisTemplate<String, NotificationMessage> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(
                new Jackson2JsonRedisSerializer<>(NotificationMessage.class));
        return redisTemplate;
    }
}
