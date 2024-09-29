package com.nbe2.api.post;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.post.dto.LocalPostPageRequest;
import com.nbe2.api.post.dto.PostRegisterRequest;
import com.nbe2.api.post.dto.PostUpdateRequest;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.posts.service.PostService;
import com.nbe2.domain.posts.service.dto.PostDetailsCommand;
import com.nbe2.domain.posts.service.dto.PostListCommand;

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
        Long postId = postService.savePost(request.toCommand(username));
        return Response.success(postId);
    }

    @GetMapping
    public Response<PageResult<PostListCommand>> getLocalPostPage(
            @Validated final LocalPostPageRequest request) {
        PageResult<PostListCommand> postPage =
                postService.findPostListPageByCity(request.toCommand());
        return Response.success(postPage);
    }

    @GetMapping("/{postsId}")
    public Response<PostDetailsCommand> getPostDetails(
            @PathVariable("postsId") final Long postsId) {
        PostDetailsCommand postDetails = postService.findPostDetails(postsId);
        return Response.success(postDetails);
    }

    @PutMapping("/{postsId}")
    public Response<Long> putPost(
            @PathVariable("postsId") final Long postsId,
            @Validated final PostUpdateRequest request) {
        Long postId = postService.updatePost(request.toCommand(postsId, request));
        return Response.success(postId);
    }

    @DeleteMapping("/{postsId}")
    public Response<Void> deletePost(@PathVariable("postsId") final Long postsId) {
        postService.deletePost(postsId);
        return Response.success();
    }
}
