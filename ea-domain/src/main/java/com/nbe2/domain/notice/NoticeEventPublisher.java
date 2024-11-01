package com.nbe2.domain.notice;

public interface NoticeEventPublisher {

    void publish(NewNoticeOfBookmarkedHospitalEvent event);
}
