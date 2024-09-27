package com.nbe2.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nbe2.domain.emergencyroom.EmergencyRoom;

public interface EmergencyRoomRepository extends JpaRepository<EmergencyRoom, Long> {

    List<EmergencyRoom> findByHospitalNameContaining(String name);
}
