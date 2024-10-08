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

    public void deleteAllInBatch() {
        jdbcTemplate.update("DELETE FROM post_files");
    }

    public void saveAll(final List<PostFile> postFiles) {
        jdbcTemplate.batchUpdate(
                """
                        INSERT INTO post_files (file_id, post_id)
                         VALUES (?, ?)
                        """,
                postFiles,
                postFiles.size(),
                (PreparedStatement ps, PostFile postFile) -> {
                    ps.setLong(1, postFile.getFileMetaDataId());
                    ps.setLong(2, postFile.getPostId());
                });
    }
}
