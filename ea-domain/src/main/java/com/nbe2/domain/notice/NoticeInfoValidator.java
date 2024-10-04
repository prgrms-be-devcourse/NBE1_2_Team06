package com.nbe2.domain.notice;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.notice.exception.NoticeNotFoundContentException;
import com.nbe2.domain.notice.exception.NoticeNotFoundTitleExcepion;

@Component
@RequiredArgsConstructor
public class NoticeInfoValidator {

    public void validate(Optional<String> title, Optional<String> content) {
        title.orElseThrow(() -> NoticeNotFoundTitleExcepion.EXCEPTION);
        content.orElseThrow(() -> NoticeNotFoundContentException.EXCEPTION);
    }
}
