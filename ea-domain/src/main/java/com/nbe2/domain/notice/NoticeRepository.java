package com.nbe2.domain.notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(
            "SELECT new com.nbe2.domain.notice.NoticeReadInfo(e.id, n.title, n.content) "
                    + "FROM Notice n JOIN n.emergencyRoom e "
                    + "WHERE e.id = :emergencyRoomId "
                    + "ORDER BY n.updatedAt ASC")
    List<NoticeReadInfo> findByEmergencyRoomId(long emergencyRoomId);
}
