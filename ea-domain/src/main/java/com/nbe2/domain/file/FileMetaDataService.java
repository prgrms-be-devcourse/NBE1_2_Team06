package com.nbe2.domain.file;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileMetaDataService {

    private final FileMetaDataAppender fileMetaDataAppender;
    private final FileMetaDataReader fileMetaDataReader;

    public Long save(FileMetaData fileMetaData) {
        return fileMetaDataAppender.append(fileMetaData);
    }

    public FileMetaData getFileMetaData(Long fileId) {
        return fileMetaDataReader.read(fileId);
    }
}
