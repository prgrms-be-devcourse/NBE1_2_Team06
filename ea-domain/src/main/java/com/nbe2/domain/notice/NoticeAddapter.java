package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticeAddapter {
    private final NoticeService noticeService;

    // 서비스로 보내자.. 뭐였더라
}
