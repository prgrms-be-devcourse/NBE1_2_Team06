package com.nbe2.api.post;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.post.dto.CommentRegisterRequest;
import com.nbe2.domain.posts.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentApi {
    private final CommentService commentService;

    // ToDo : SpringSecurity 의 @AuthenticationPrincipal 사용
    @PostMapping
    public Response<Long> postComment(
            @RequestParam("postsId") final Long postsId,
            @RequestBody @Validated
                    final CommentRegisterRequest request
                            //            , @AuthenticationPrincipal Long id (userId)
                            ,
            @RequestParam("id") final Long id) {
        Long postId = commentService.save(postsId, id, request.content());
        return Response.success(postId);
    }
}
