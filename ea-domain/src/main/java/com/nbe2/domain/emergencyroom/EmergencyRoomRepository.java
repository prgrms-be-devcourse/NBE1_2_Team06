package com.nbe2.domain.emergencyroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRoomRepository
        extends JpaRepository<EmergencyRoom, Long>, EmergencyRoomRepositoryCustom {

    List<EmergencyRoom> findByHospitalNameContaining(String name);

    Optional<EmergencyRoom> findByHpId(String hpId);

    Optional<EmergencyRoom> findByHospitalName(String hospitalName);
}
