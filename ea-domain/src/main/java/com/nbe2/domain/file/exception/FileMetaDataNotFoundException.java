package com.nbe2.domain.file.exception;

import com.nbe2.common.exception.DomainException;

public class FileMetaDataNotFoundException extends DomainException {

    public static final FileMetaDataNotFoundException EXCEPTION =
            new FileMetaDataNotFoundException();

    private FileMetaDataNotFoundException() {
        super(FileMetaDataErrorCode.FILE_META_DATA_NOT_FOUND_ERROR);
    }
}
