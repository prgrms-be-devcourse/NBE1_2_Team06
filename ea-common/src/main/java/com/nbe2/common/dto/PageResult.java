package com.nbe2.common.dto;

import java.util.List;
import java.util.function.Function;

public record PageResult<T>(List<T> content, int totalPage, boolean hasNext) {

    public static <T> PageResult<T> of(List<T> content, int totalPage, boolean hasNext) {
        return new PageResult<>(content, totalPage, hasNext);
    }

    public <R> PageResult<R> map(Function<T, R> mapper) {
        List<R> mappedContent = content.stream().map(mapper).toList();
        return of(mappedContent, totalPage, hasNext);
    }
}
