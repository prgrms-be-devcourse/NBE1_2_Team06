package com.nbe2.domain.notice;

public record NewNoticeOfBookmarkedHospitalEvent(
        long targetId, String referenceUri, String hospitalName) {

    public static NewNoticeOfBookmarkedHospitalEvent of(long targetId, Notice notice) {
        return new NewNoticeOfBookmarkedHospitalEvent(
                targetId,
                "/emergency-rooms/" + notice.getEmergencyRoom().getId(),
                notice.getEmergencyRoom().getHospitalName());
    }
}
