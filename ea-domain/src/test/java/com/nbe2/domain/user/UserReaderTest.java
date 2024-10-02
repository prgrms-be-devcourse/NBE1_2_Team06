package com.nbe2.domain.user;

import static com.nbe2.domain.global.TestConstants.EMAIL;
import static com.nbe2.domain.global.TestConstants.ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.user.exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
class UserReaderTest {

    @InjectMocks private UserReader userReader;

    @Mock private UserRepository userRepository;

    @Nested
    @DisplayName("ID로 사용자를 조회한다.")
    class ReadByIdTest {

        @Test
        @DisplayName("유효한 ID 전달 시 사용자를 반환한다.")
        void given_user_id_when_user_exists_then_should_return_user() {
            // given
            long userId = ID;
            User expected = UserFixture.createUser();

            // when
            when(userRepository.findById(anyLong())).thenReturn(Optional.of(expected));

            // then
            User actual = userReader.read(userId);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("존재하지 않는 사용자 조회 시 예외가 발생한다.")
        void given_user_id_when_user_not_exists_then_should_throw_exception() {
            // given
            long userId = ID;

            // when
            when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

            // then
            assertThrows(UserNotFoundException.class, () -> userReader.read(userId));
        }
    }

    @Nested
    @DisplayName("이메일로 사용자를 조회한다.")
    class ReadByEmailTest {

        @Test
        @DisplayName("유효한 이메일 전달 시 사용자를 반환한다.")
        void given_email_when_user_exists_then_should_return_user() {
            // given
            String email = EMAIL;
            User expected = UserFixture.createUser();

            // when
            when(userRepository.findByEmail(email)).thenReturn(Optional.of(expected));

            // then
            User actual = userReader.read(email);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("존재하지 않는 이메일로 조회 시 예외가 발생한다.")
        void given_email_when_user_not_exists_then_should_throw_exception() {
            // given
            String email = EMAIL;

            // when
            when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

            // then
            assertThrows(UserNotFoundException.class, () -> userReader.read(email));
        }
    }

    @Nested
    @DisplayName("대기 상태 회원 목록을 조회한다.")
    class PendingUserReadTest {

        @Test
        @DisplayName("대기 상태 관리자가 없을 때 빈 페이지가 조회된다.")
        void given_pageable_when_pending_user_does_not_exist_then_should_return_empty_page() {
            // given
            Pageable pageable = PageRequest.of(0, 10);
            Page<UserProfileWithLicense> emptyPage =
                    new PageImpl<>(Collections.emptyList(), pageable, 0);

            // when
            when(userRepository.findPageByApprovalStatus(
                            any(ApprovalStatus.class), any(Pageable.class)))
                    .thenReturn(emptyPage);
            PageResult<UserProfileWithLicense> actual = userReader.read(pageable);

            // then
            assertTrue(actual.content().isEmpty());
            assertEquals(0, actual.totalPage());
            assertFalse(actual.hasNext());
        }

        @Test
        @DisplayName("대기 상태 관리자가 있을 때 페이지로 조회된다.")
        void given_pageable_when_pending_user_exists_then_should_return_page_with_content() {
            // given
            Pageable pageable = PageRequest.of(0, 5);
            List<UserProfileWithLicense> content =
                    Collections.nCopies(10, UserFixture.createUserProfileWithLicense());
            Page<UserProfileWithLicense> page = new PageImpl<>(content, pageable, content.size());

            // when
            when(userRepository.findPageByApprovalStatus(
                            any(ApprovalStatus.class), any(Pageable.class)))
                    .thenReturn(page);
            PageResult<UserProfileWithLicense> actual = userReader.read(pageable);

            // then
            assertEquals(content.size(), actual.content().size());
            assertEquals(2, actual.totalPage());
            assertTrue(actual.hasNext());
        }

        @Test
        @DisplayName("마지막 페이지를 조회한다.")
        void given_pageable_when_pending_user_exists_then_should_return_last_page() {
            // given
            Pageable pageable = PageRequest.of(0, 5);
            List<UserProfileWithLicense> content =
                    Collections.nCopies(5, UserFixture.createUserProfileWithLicense());
            Page<UserProfileWithLicense> page = new PageImpl<>(content, pageable, content.size());

            // when
            when(userRepository.findPageByApprovalStatus(
                            any(ApprovalStatus.class), any(Pageable.class)))
                    .thenReturn(page);
            PageResult<UserProfileWithLicense> actual = userReader.read(pageable);

            // then
            assertEquals(content.size(), actual.content().size());
            assertEquals(1, actual.totalPage());
            assertFalse(actual.hasNext());
        }
    }
}
