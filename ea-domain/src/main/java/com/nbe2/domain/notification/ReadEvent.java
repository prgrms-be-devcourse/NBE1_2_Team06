package com.nbe2.domain.notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadEvent {

    private Long ownerId;

    public static ReadEvent of(Long userId) {
        return new ReadEvent(userId);
    }
}
