package com.nbe2.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nbe2.domain.emergencyroom.EmergencyRoom;

public interface EmergencyRoomRepository extends JpaRepository<EmergencyRoom, Long> {

    Optional<List<EmergencyRoom>> findByHospitalNameContaining(String name);
}
