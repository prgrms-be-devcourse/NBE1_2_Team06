package com.nbe2.domain.bookmark;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(Long userId);

    Bookmark findByEmergencyRoomId(Long emergencyRoomId);
}
