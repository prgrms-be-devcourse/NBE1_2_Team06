package com.nbe2.api.user.dto;

import com.nbe2.domain.user.MedicalProfile;

public record MedicalRequest(Long emergencyRoomId, Long licenseId) {

    public MedicalProfile toMedicalProfile() {
        return new MedicalProfile(emergencyRoomId, licenseId);
    }
}
