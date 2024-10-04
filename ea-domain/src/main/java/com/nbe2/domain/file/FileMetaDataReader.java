package com.nbe2.domain.file;

import java.util.List;

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

    public List<FileMetaData> readAll(List<Long> fileIds) {
        return fileMetaDateRepository.findAllById(fileIds);
    }
}
