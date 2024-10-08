package com.nbe2.api.post;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.post.dto.LikeRequest;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.posts.LikeInfo;
import com.nbe2.domain.posts.LikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeApi {
    private final LikeService likeService;

    @PostMapping
    public Response<Void> postLike(
            @RequestBody final LikeRequest request,
            @AuthenticationPrincipal final UserPrincipal userPrincipal) {
        likeService.addLike(LikeInfo.of(request.postsId(), userPrincipal.userId()));
        return Response.success();
    }

    @DeleteMapping
    public Response<Void> deleteLike(
            @RequestBody final LikeRequest request,
            @AuthenticationPrincipal final UserPrincipal userPrincipal) {
        likeService.cancelLike(LikeInfo.of(request.postsId(), userPrincipal.userId()));
        return Response.success();
    }
}
