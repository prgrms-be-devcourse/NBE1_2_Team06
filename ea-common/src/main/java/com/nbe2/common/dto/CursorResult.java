package com.nbe2.common.dto;

import java.util.List;
import java.util.function.Function;

public record CursorResult<T>(List<T> content, Long nextCursor) {

    public static <T> CursorResult<T> of(List<T> content, Long nextCursor) {
        return new CursorResult<>(content, nextCursor);
    }

    public <R> CursorResult<R> map(Function<T, R> mapper) {
        List<R> mappedContent = content.stream().map(mapper).toList();
        return of(mappedContent, nextCursor);
    }
}
