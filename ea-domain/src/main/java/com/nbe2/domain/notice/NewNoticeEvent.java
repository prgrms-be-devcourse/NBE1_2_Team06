package com.nbe2.domain.notice;

public record NewNoticeEvent(Notice notice) {

    public static NewNoticeEvent from(Notice notice) {
        return new NewNoticeEvent(notice);
    }
}
