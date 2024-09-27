package com.nbe2.domain.notice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Long save(NoticeInfo noticeInfo, Long userId, Long emergencyRoomId);
}
