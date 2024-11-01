package com.nbe2.domain.posts;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostFileRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String DELETE_BY_POST = "DELETE FROM post_files WHERE post_id = ?";
    private static final String SAVE_ALL =
            """
            INSERT INTO post_files (file_id, post_id)
             VALUES (?, ?)
            """;

    public void deleteByPostId(Long postId) {
        jdbcTemplate.update(DELETE_BY_POST, ps -> ps.setLong(1, postId));
    }

    public void saveAll(final List<PostFile> postFiles) {
        jdbcTemplate.batchUpdate(
                SAVE_ALL,
                postFiles,
                postFiles.size(),
                (PreparedStatement ps, PostFile postFile) -> {
                    ps.setLong(1, postFile.getFileMetaDataId());
                    ps.setLong(2, postFile.getPostId());
                });
    }
}
