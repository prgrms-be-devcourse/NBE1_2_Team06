package com.nbe2.domain.review;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Page<Review>> findByEmergencyRoomId(Long emergencyRoomId, Pageable pageable);
}
