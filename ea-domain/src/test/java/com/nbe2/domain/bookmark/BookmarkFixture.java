package com.nbe2.domain.bookmark;

import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomFixture;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;

public class BookmarkFixture {

    public static Bookmark createBookmark() {
        User user = UserFixture.createUserWithId(); // ID가 있는 유저 생성
        EmergencyRoom emergencyRoom = EmergencyRoomFixture.create(); // 병원 객체 생성
        return Bookmark.from(user, emergencyRoom);
    }

    public static Bookmark createBookmark(User user, EmergencyRoom emergencyRoom) {
        return Bookmark.from(user, emergencyRoom);
    }
}
