package com.nbe2.domain.file;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.file.exception.FileMetaDataNotFoundException;

@Component
@RequiredArgsConstructor
public class FileMetaDataReader {

    private final FileMetaDateRepository fileMetaDateRepository;

    public FileMetaData read(Long fileId) {
        return fileMetaDateRepository
                .findById(fileId)
                .orElseThrow(() -> FileMetaDataNotFoundException.EXCEPTION);
    }
}
