package com.nbe2.domain.posts;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.file.FileMetaData;
import com.nbe2.domain.file.FileMetaDataReader;

@Component
@RequiredArgsConstructor
public class PostFileSetter {
    private final FileMetaDataReader fileMetaDataReader;

    public void set(final Post post, final Optional<List<Long>> fileIdList) {
        fileIdList.ifPresent(
                (fileIds) -> {
                    List<FileMetaData> fileMetaDataList = fileMetaDataReader.readAll(fileIds);
                    post.resetPostFiles();
                    fileMetaDataList.stream()
                            .map(file -> PostFile.of(file, post))
                            .forEach(post::addFile);
                });
    }
}
