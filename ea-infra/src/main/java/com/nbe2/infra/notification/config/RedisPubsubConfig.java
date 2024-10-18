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

import com.nbe2.domain.notification.NotificationType;
import com.nbe2.infra.notification.subscriber.CommentRedisSubscriber;
import com.nbe2.infra.notification.subscriber.NoticeRedisSubscriber;

@Configuration
@RequiredArgsConstructor
public class RedisPubsubConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            MessageListenerAdapter noticeListener, MessageListenerAdapter commentListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(
                noticeListener, new PatternTopic(NotificationType.NOTICE.getChannelId()));
        container.addMessageListener(
                commentListener, new PatternTopic(NotificationType.COMMENT.getChannelId()));
        return container;
    }

    @Bean
    public MessageListenerAdapter noticeListener(NoticeRedisSubscriber noticeRedisSubscriber) {
        return new MessageListenerAdapter(noticeRedisSubscriber);
    }

    @Bean
    public MessageListenerAdapter commentListener(CommentRedisSubscriber commentRedisSubscriber) {
        return new MessageListenerAdapter(commentRedisSubscriber);
    }

    @Bean
    public RedisTemplate<String, Object> notificationTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return redisTemplate;
    }
}
