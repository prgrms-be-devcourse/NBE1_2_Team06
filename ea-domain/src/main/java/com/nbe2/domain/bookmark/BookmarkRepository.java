package com.nbe2.domain.bookmark;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(Long userId);

    Optional<Bookmark> findByEmergencyRoomIdAndUserId(Long emergencyRoomId, Long userId);

    @Query("SELECT b.user.id FROM Bookmark b WHERE b.emergencyRoom.id = :emergencyRoomId")
    List<Long> findUserIdsByEmergencyRoomId(Long emergencyRoomId);
}
