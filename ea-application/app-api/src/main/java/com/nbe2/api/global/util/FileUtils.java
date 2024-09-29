package com.nbe2.api.global.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.nbe2.api.global.exception.FileNotFoundException;
import com.nbe2.domain.file.FileMetaData;

public class FileUtils {

    private static final String BASE_PATH = getBasePath();
    private static final String RELATIVE_UPLOAD_PATH = "files/";
    private static final String UPLOAD_PATH = BASE_PATH + RELATIVE_UPLOAD_PATH;

    private static String getBasePath() {
        try {
            return Paths.get(
                                    FileUtils.class
                                            .getProtectionDomain()
                                            .getCodeSource()
                                            .getLocation()
                                            .toURI())
                            .getParent()
                            .getParent()
                            .getParent()
                            .getParent()
                            .toString()
                    + "/";
        } catch (Exception e) {
            throw new RuntimeException("Cannot determine base path.", e);
        }
    }

    public static FileMetaData saveFile(MultipartFile uploadFile) {

        if (uploadFile == null) {
            return null;
        }

        if (!new File(UPLOAD_PATH).exists()) {
            new File(UPLOAD_PATH).mkdir();
        }

        String savedName = UUID.randomUUID().toString();
        File savedTradingFile = new File(UPLOAD_PATH + savedName);

        try {
            uploadFile.transferTo(savedTradingFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return FileMetaData.of(uploadFile.getOriginalFilename(), UPLOAD_PATH + savedName);
    }

    public static File validate(String path) {
        File file = new File(path);

        if (!file.exists()) {
            throw FileNotFoundException.EXCEPTION;
        }
        return file;
    }

    public static String probeContentType(File file) {
        try {
            return Files.probeContentType(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
