package com.nbe2.api.global.exception;

import com.nbe2.common.exception.GlobalErrorCode;
import com.nbe2.common.exception.WebException;

public class FileNotFoundException extends WebException {

    public static final FileNotFoundException EXCEPTION = new FileNotFoundException();

    private FileNotFoundException() {
        super(GlobalErrorCode.FILE_NOT_FOUND);
    }
}
