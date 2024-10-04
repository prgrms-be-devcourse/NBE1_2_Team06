package com.nbe2.domain.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findByEmergencyRoomId(Pageable pageable, Long emergencyRoomId);

    @Query(
            "SELECT n FROM Notice n "
                    + "JOIN FETCH n.emergencyRoom e "
                    + "LEFT JOIN FETCH n.noticeFiles f "
                    + "WHERE e.id = :emergencyRoomId "
                    + "AND n.title = :title "
                    + "ORDER BY n.updatedAt ASC")
    Page<Notice> findByEmergencyRoomIdAndTitle(
            Pageable pageable, Long emergencyRoomId, String title);
}
