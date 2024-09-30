package com.nbe2.domain.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(
            "SELECT new com.nbe2.domain.notice.NoticeReadInfo(e.id, n.title, n.content) "
                    + "FROM Notice n JOIN n.emergencyRoom e "
                    + "WHERE e.id = :emergencyRoomId "
                    + "ORDER BY n.updatedAt ASC")
    Page<NoticeReadInfo> findByEmergencyRoomIdPage(Pageable pageable, Long emergencyRoomId);

    @Query(
            "SELECT new com.nbe2.domain.notice.NoticeReadInfo(e.id, n.title, n.content) "
                    + "FROM Notice n JOIN n.emergencyRoom e "
                    + "WHERE e.id = :emergencyRoomId "
                    + "AND n.title = :title "
                    + "ORDER BY n.updatedAt ASC")
    Page<NoticeReadInfo> findByTitle(Pageable pageable, Long emergencyRoomId, String title);
}
