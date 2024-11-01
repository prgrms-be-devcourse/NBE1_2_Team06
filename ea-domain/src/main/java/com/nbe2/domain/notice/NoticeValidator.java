package com.nbe2.domain.notice;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.notice.exception.NoticeNoAccessCreateException;
import com.nbe2.domain.notice.exception.NoticeNoAccessYoursException;
import com.nbe2.domain.user.UserRole;

@Component
@RequiredArgsConstructor
public class NoticeValidator {

    // 수정, 삭제 시 본인이 작성한 공지사항이 맞는지 검증
    public boolean validateAuthor(Notice notice, Long userId) {
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
