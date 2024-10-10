package com.nbe2.domain.bookmark;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(Long userId);

    Optional<Bookmark> findByEmergencyRoomIdAndUserId(Long emergencyRoomId, Long userId);
}
