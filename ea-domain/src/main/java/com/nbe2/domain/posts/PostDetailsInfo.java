package com.nbe2.domain.posts;

public record PostDetailsInfo(
        Long id,
        String name,
        String title,
        String content,
        Long likeCount,
        Long commentCount,
        List<Long> fileIds) {
    public static PostDetailsInfo from(Post post) {
        return new PostDetailsInfo(
                post.getId(),
                post.getWriterName(),
                post.getTitle(),
                post.getContent(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getPostFiles().stream().map(PostFile::getFileMetaDataId).toList());
    }
}
