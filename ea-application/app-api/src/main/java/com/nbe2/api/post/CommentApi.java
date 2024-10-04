package com.nbe2.api.post;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.post.dto.CommentRegisterRequest;
import com.nbe2.api.post.dto.CommentResponse;
import com.nbe2.api.post.dto.CommentUpdateRequest;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.posts.service.CommentService;
import com.nbe2.domain.posts.service.dto.CommentInfo;
import com.nbe2.domain.posts.service.dto.CommentReadInfo;
import com.nbe2.domain.posts.service.dto.CommentWriteInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@Slf4j
public class CommentApi {
    private final CommentService commentService;

    @PostMapping
    public Response<Long> postComment(
            @RequestBody @Validated final CommentRegisterRequest request,
            @AuthenticationPrincipal final UserPrincipal userPrincipal
            //            @RequestParam("id") final Long id
            ) {
        Long postId =
                commentService.save(
                        request.postsId(),
                        //                        id
                        CommentWriteInfo.create(
                                userPrincipal.userId(), CommentInfo.of(request.content())));
        return Response.success(postId);
    }

    @GetMapping
    Response<List<CommentResponse>> getPostComments(@RequestParam("postsId") Long postId) {
        List<CommentReadInfo> commentReadInfos = commentService.getPostComments(postId);
        List<CommentResponse> commentResponses =
                commentReadInfos.stream().map(CommentResponse::from).toList();
        return Response.success(commentResponses);
    }

    @PutMapping("/{commentsId}")
    public Response<Long> putComment(
            @PathVariable("commentsId") final Long commentsId,
            @RequestBody final CommentUpdateRequest request,
            @AuthenticationPrincipal final UserPrincipal userPrincipal
            //            @RequestParam("id") final Long id
            ) {
        Long postId =
                commentService.update(
                        //                        id
                        commentsId,
                        CommentWriteInfo.create(
                                userPrincipal.userId(), CommentInfo.of(request.content())));
        return Response.success(postId);
    }

    @DeleteMapping("{commentsId}")
    public Response<Void> deleteComment(@PathVariable("commentsId") final Long commentsId) {
        commentService.delete(commentsId);
        return Response.success();
    }
}
