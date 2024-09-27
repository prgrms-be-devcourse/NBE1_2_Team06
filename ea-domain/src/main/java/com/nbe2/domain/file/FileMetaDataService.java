package com.nbe2.domain.file;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileMetaDataService {

    private final FileMetaDataAppender fileMetaDataAppender;
    private final FileMetaDataReader fileMetaDataReader;
}
