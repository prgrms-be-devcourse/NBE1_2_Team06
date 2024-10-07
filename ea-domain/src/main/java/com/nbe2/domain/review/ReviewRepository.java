package com.nbe2.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByEmergencyRoomId(Long emergencyRoomId, Pageable pageable);

    Page<Review> findByUserId(Long userId, Pageable pageable);
}
