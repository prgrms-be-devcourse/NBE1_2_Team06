package com.nbe2.api.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.user.dto.MedicalRequest;
import com.nbe2.api.user.dto.ProfileResponse;
import com.nbe2.api.user.dto.UpdatePasswordRequest;
import com.nbe2.api.user.dto.UpdateProfileRequest;
import com.nbe2.common.annotation.PageDefault;
import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.posts.service.dto.PostListInfo;
import com.nbe2.domain.user.UserService;

@RestController
@RequestMapping("/api/v1/my")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PatchMapping("/medical")
    public Response<Void> requestMedicalAuthority(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody MedicalRequest medicalRequest) {
        userService.requestMedicalAuthority(
                userPrincipal.userId(), medicalRequest.toMedicalProfile());
        return Response.success();
    }

    @GetMapping
    public Response<ProfileResponse> getMyProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return Response.success(
                ProfileResponse.from(userService.getMyProfile(userPrincipal.userId())));
    }

    @PatchMapping
    public Response<Void> updateMyProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UpdateProfileRequest request) {
        userService.updateProfile(userPrincipal.userId(), request.toProfile());
        return Response.success();
    }

    @PatchMapping("/password")
    public Response<Void> changePassword(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UpdatePasswordRequest request) {
        userService.changePassword(userPrincipal.userId(), request.toPassword());
        return Response.success();
    }

    @GetMapping("/posts")
    public Response<PageResult<PostListInfo>> getMyPostPage(
            @AuthenticationPrincipal final UserPrincipal userPrincipal,
            @PageDefault final Page page) {
        PageResult<PostListInfo> postPage = userService.getMyPosts(page, userPrincipal.userId());
        return Response.success(postPage);
    }
}
