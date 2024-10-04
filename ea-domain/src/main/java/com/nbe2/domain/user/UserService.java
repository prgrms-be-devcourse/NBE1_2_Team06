package com.nbe2.domain.user;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.emergencyroom.EmergencyRoom;
import com.nbe2.domain.emergencyroom.EmergencyRoomReader;
import com.nbe2.domain.file.FileMetaData;
import com.nbe2.domain.file.FileMetaDataReader;
import com.nbe2.domain.global.util.PagingUtil;
import com.nbe2.domain.posts.service.component.PostReader;
import com.nbe2.domain.posts.service.dto.PostListInfo;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EmergencyRoomReader emergencyRoomReader;
    private final FileMetaDataReader fileMetaDataReader;
    private final UserReader userReader;
    private final UserUpdater userUpdater;
    private final PostReader postReader;

    @Transactional
    public void requestMedicalAuthority(Long userId, MedicalProfile medicalProfile) {
        EmergencyRoom emergencyRoom = emergencyRoomReader.read(medicalProfile.emergencyRoomId());
        FileMetaData license = fileMetaDataReader.read(medicalProfile.licenseId());
        User user = userReader.read(userId);
        userUpdater.requestMedicalRole(user, emergencyRoom, license);
    }

    public MyProfile getMyProfile(long userId) {
        return MyProfile.from(userReader.read(userId));
    }

    public void updateProfile(long userId, UpdateProfile profile) {
        User user = userReader.read(userId);
        userUpdater.update(user, profile);
    }

    public void changePassword(long userId, UpdatePassword password) {
        User user = userReader.read(userId);
        userUpdater.update(user, password);
    }

    public PageResult<PostListInfo> getMyPosts(Page page, long userId) {
        User user = userReader.read(userId);
        return postReader.readListPage(PagingUtil.toPageRequest(page), user);
    }
}
