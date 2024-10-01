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

    /* @Query(
            "SELECT n FROM Notice n "
                    + "JOIN FETCH n.emergencyRoom e "
                    + "LEFT JOIN FETCH n.noticeFiles f "
                    + "WHERE e.id = :emergencyRoomId "
                    + "ORDER BY n.updatedAt ASC")
    Page<Notice> findByEmergencyRoomIdPage(Pageable pageable, @Param("emergencyRoomId") Long emergencyRoomId);*/

    /*
    @Query(
            "SELECT new com.nbe2.domain.notice.NoticeReadInfo(e.id, n.title, n.content, f.fileMetaData.id)  "
                    + "FROM Notice n "
                    + "JOIN n.emergencyRoom e "
                    + "LEFT JOIN NoticeFile f ON f.notice.noticeId = n.noticeId "
                    + "WHERE e.id = :emergencyRoomId "
                    + "AND n.title = :title "
                    + "GROUP BY e.id, n.title, n.content, n.updatedAt "
                    + "ORDER BY n.updatedAt ASC")
    Page<NoticeReadInfo> findByTitle(Pageable pageable, Long emergencyRoomId, String title);*/
}
