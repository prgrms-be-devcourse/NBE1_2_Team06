package com.nbe2.api.post;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.post.dto.PostRegisterRequest;
import com.nbe2.api.post.dto.PostResponse;
import com.nbe2.api.post.dto.PostUpdateRequest;
import com.nbe2.common.annotation.PageDefault;
import com.nbe2.common.dto.Page;
import com.nbe2.common.dto.PageResult;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.posts.City;
import com.nbe2.domain.posts.PostDetailsInfo;
import com.nbe2.domain.posts.PostListInfo;
import com.nbe2.domain.posts.PostService;
import com.nbe2.domain.posts.PostWriteInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostApi {

    private final PostService postService;

    @PostMapping
    public Response<Long> postPost(
            @RequestBody @Validated final PostRegisterRequest request,
            @AuthenticationPrincipal final UserPrincipal userPrincipal,
            @RequestParam(name = "file", required = false) List<Long> fileIds) {
        Long postId =
                postService.save(
                        userPrincipal.userId(),
                        PostWriteInfo.create(
                                request.title(),
                                request.content(),
                                request.city(),
                                Optional.ofNullable(fileIds)));
        return Response.success(postId);
    }

    @GetMapping
    public Response<PageResult<PostListInfo>> getLocalPostPage(
            @RequestParam("city") final City city, final @PageDefault Page page) {
        PageResult<PostListInfo> postPage = postService.findListPageByCity(page, city);
        return Response.success(postPage);
    }

    @GetMapping("/{postsId}")
    public Response<PostResponse> getPostDetails(@PathVariable("postsId") final Long postsId) {
        PostDetailsInfo postDetailsInfo = postService.findDetails(postsId);
        return Response.success(PostResponse.from(postDetailsInfo));
    }

    @PutMapping("/{postsId}")
    public Response<Long> putPost(
            @PathVariable("postsId") final Long postsId,
            @RequestBody @Validated final PostUpdateRequest request,
            @RequestParam(name = "file", required = false) List<Long> fileIds) {
        Long postId =
                postService.update(
                        postsId,
                        PostWriteInfo.create(
                                request.title(),
                                request.content(),
                                request.city(),
                                Optional.ofNullable(fileIds)));
        return Response.success(postId);
    }

    @DeleteMapping("/{postsId}")
    public Response<Void> deletePost(@PathVariable("postsId") final Long postsId) {
        postService.delete(postsId);
        return Response.success();
    }
}
