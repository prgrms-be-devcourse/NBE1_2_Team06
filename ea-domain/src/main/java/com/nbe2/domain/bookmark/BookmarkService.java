package com.nbe2.domain.bookmark;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserReader;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkAppender bookmarkAppender;
    private final BookmarkReader bookmarkReader;
    private final BookmarkDeleter bookmarkDeleter;
    private final UserReader userReader;

    public void saveBookmark(Long emergencyRoomId, UserPrincipal userPrincipal) {
        bookmarkAppender.save(emergencyRoomId, userPrincipal.userId());
    }

    public BookmarkReadInfo readBookmark(UserPrincipal userPrincipal) {
        User user = userReader.read(userPrincipal.userId());
        List<Bookmark> bookmarks = bookmarkReader.readByUserId(user.getId());

        return BookmarkReadInfo.from(bookmarks);
    }

    public void deleteBookmark(Long emergencyRoomId, UserPrincipal userPrincipal) {
        User user = userReader.read(userPrincipal.userId());
        Bookmark bookmark = bookmarkReader.readByEmergencyRoomId(emergencyRoomId);
        // 유저가 추가한 즐겨찾기가 맞는지 검증 -> 테스트하고 Validate.class로 빼기
        if (!user.getId().equals(bookmark.getUser().getId())) {
            return;
        }
        // 즐겨찾기한 병원이 맞는지?
        bookmarkDeleter.deleteBookmark(bookmarkId);
    }
}
