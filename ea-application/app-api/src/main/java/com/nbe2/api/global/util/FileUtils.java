package com.nbe2.api.global.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.nbe2.domain.file.FileMetaData;

@Component
public class FileUtils {

    private static String uploadPath;

    @Value("${file.upload.path}")
    public static void setUploadPath(String uploadPath) {
        FileUtils.uploadPath = uploadPath;
    }

    public static FileMetaData saveFile(MultipartFile uploadFile) {

        if (uploadFile == null) {
            return null;
        }

        if (!new File(uploadPath).exists()) {
            new File(uploadPath).mkdir();
        }

        String savedName = UUID.randomUUID().toString();
        File savedTradingFile = new File(uploadPath + savedName);

        try {
            uploadFile.transferTo(savedTradingFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return FileMetaData.of(uploadFile.getOriginalFilename(), uploadPath + savedName);
    }

    public static File validate(String path) {
        File file = new File(path);

        if (!file.exists()) {
            try {
                throw new FileNotFoundException("FileMetaData not found: " + path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }
}
