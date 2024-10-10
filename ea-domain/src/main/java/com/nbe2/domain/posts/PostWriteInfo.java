package com.nbe2.domain.posts;

import java.util.List;
import java.util.Optional;

// create, update 에 사용되는 정보
public record PostWriteInfo(
        String title, String content, City city, Optional<List<Long>> fileIdList) {
    public static PostWriteInfo create(
            final String title,
            final String content,
            final City city,
            final Optional<List<Long>> fileIdList) {
        return new PostWriteInfo(title, content, city, fileIdList);
    }
}
