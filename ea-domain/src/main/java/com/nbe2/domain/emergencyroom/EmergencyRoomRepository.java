package com.nbe2.domain.emergencyroom;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRoomRepository extends JpaRepository<EmergencyRoom, Long> {

    Optional<EmergencyRoom> findByHpId(String hpId);
}
