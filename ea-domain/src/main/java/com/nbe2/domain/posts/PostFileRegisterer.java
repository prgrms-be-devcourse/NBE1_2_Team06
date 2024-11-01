package com.nbe2.domain.posts;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.file.FileMetaData;
import com.nbe2.domain.file.FileMetaDataReader;

@Component
@RequiredArgsConstructor
public class PostFileRegisterer {
    private final PostFileRepository postFileRepository;
    private final FileMetaDataReader fileMetaDataReader;

    public void register(final Post post, final Optional<List<Long>> fileIdList) {
        postFileRepository.deleteByPostId(post.getId());
        fileIdList.ifPresent(
                fileIds -> {
                    List<FileMetaData> fileMetaDatas = fileMetaDataReader.readAll(fileIds);
                    List<PostFile> postFiles =
                            fileMetaDatas.stream().map(data -> PostFile.of(data, post)).toList();
                    postFileRepository.saveAll(postFiles);
                });
    }
}
