package com.nbe2.domain.file;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileMetaDataAppender {

    private final FileMetaDateRepository fileMetaDateRepository;

    public Long append(FileMetaData fileMetaData) {
        FileMetaData saved = fileMetaDateRepository.save(fileMetaData);
        return saved.getId();
    }
}
