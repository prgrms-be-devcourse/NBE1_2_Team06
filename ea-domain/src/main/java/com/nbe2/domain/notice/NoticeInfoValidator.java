package com.nbe2.domain.notice;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.notice.exception.NoticeNoAccessCreateException;
import com.nbe2.domain.notice.exception.NoticeNoAccessYoursException;
import com.nbe2.domain.notice.exception.NoticeNotFoundContentException;
import com.nbe2.domain.notice.exception.NoticeNotFoundTitleExcepion;
import com.nbe2.domain.user.UserRole;

@Component
@RequiredArgsConstructor
public class NoticeInfoValidator {

    // 등록, 수정 시 제목, 내용이 있는지 검증
    public void validateNull(Optional<String> title, Optional<String> content) {
        title.orElseThrow(() -> NoticeNotFoundTitleExcepion.EXCEPTION);
        content.orElseThrow(() -> NoticeNotFoundContentException.EXCEPTION);
    }

    // 수정, 삭제 시 본인이 작성한 공지사항이 맞는지 검증
    public boolean validateUserId(Notice notice, Long userId) {
        if (!notice.getUser().getId().equals(userId)) {
            throw NoticeNoAccessYoursException.EXCEPTION;
        }
        return true;
    }

    // 의료 관계자 계정인가 검증
    public boolean validateRole(UserPrincipal userPrincipal) {
        if (userPrincipal.role() != UserRole.MEDICAL_PERSON) {
            throw NoticeNoAccessCreateException.EXCEPTION;
        }
        return true;
    }
}
