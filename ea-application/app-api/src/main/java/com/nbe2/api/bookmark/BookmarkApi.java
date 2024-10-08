package com.nbe2.api.bookmark;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.domain.auth.UserPrincipal;
import com.nbe2.domain.bookmark.BookmarkReadInfo;
import com.nbe2.domain.bookmark.BookmarkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bookmarks")
public class BookmarkApi {
    private final BookmarkService bookmarkService;

    @PostMapping // insert
    public Response<Void> addBookmark(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam Long emergencyRoomId) {
        bookmarkService.saveBookmark(emergencyRoomId, userPrincipal);
        return Response.success();
    }

    @GetMapping
    public Response<BookmarkReadInfo> getBookmark(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return Response.success(bookmarkService.readBookmark(userPrincipal));
    }

    @DeleteMapping("/{emergencyRommId}")
    public Response<Void> deleteBookmark(
            @PathVariable Long emergencyRommId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        bookmarkService.deleteBookmark(emergencyRommId, userPrincipal);
        return Response.success();
    }
}
