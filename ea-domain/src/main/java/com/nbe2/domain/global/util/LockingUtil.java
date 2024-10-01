package com.nbe2.domain.global.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.global.exception.IllegalLockingAttemptException;

@Component
@RequiredArgsConstructor
public class LockingUtil {
    private final EntityManager em;

    public <T> void pessimisticWriteLock(T target) {
        try {
            em.lock(target, LockModeType.PESSIMISTIC_WRITE);
        } catch (IllegalLockingAttemptException e) {
            throw IllegalLockingAttemptException.EXCEPTION;
        }
    }
}
