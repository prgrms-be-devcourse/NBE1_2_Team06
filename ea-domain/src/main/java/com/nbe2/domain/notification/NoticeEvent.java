package com.nbe2.domain.notification;

import com.nbe2.domain.notice.Notice;

public record NoticeEvent(Notice notice) {

    public static NoticeEvent of(Notice notice) {
        return new NoticeEvent(notice);
    }
}
