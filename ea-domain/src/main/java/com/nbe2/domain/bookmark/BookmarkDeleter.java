package com.nbe2.domain.bookmark;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookmarkDeleter {
    private final BookmarkRepository bookmarkRepository;

    public void deleteBookmark(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
    }
}
