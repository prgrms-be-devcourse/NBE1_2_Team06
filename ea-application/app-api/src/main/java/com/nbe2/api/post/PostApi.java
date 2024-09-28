package com.nbe2.api.post;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.post.dto.LocalPostPageRequest;
import com.nbe2.api.post.dto.PostRegisterRequest;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.posts.service.PostService;
import com.nbe2.domain.posts.service.dto.PostCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostApi {

    private final PostService postService;

    // ToDo : SpringSecurity 의 @AuthenticationPrincipal 사용
    @PostMapping
    public Response<Long> postPost(
            @Validated final PostRegisterRequest request,
            //            , @AuthenticationPrincipal String username
            @RequestParam("username") final String username) {
        Long postId = postService.registerPost(request.toCommand(username));
        return Response.success(postId);
    }

    @GetMapping
    public Response<PageResult<PostCommand>> getLocalPostPage(
            @Validated final LocalPostPageRequest request) {
        PageResult<PostCommand> postPage = postService.getPostPageByCity(request.toCommand());
        return Response.success(postPage);
    }
}
