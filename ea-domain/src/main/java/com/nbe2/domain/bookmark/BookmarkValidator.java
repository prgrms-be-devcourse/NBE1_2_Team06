package com.nbe2.domain.bookmark;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Component
@RequiredArgsConstructor
public class BookmarkValidator {
    private final UserReader userReader;
    private final BookmarkReader bookmarkReader;

    public boolean validateUser(UserPrincipal userPrincipal, Long bookmarkId) {
        User user = userReader.read(userPrincipal.userId());
        Bookmark bookmark = bookmarkReader.readById(bookmarkId);
        // 유저가 추가한 즐겨찾기가 맞는지 검증 -> 테스트하고 Validate.class로 빼기
        if (!user.getId().equals(bookmark.getUser().getId())) {
            return false;
        }
        return true;
    }
}
