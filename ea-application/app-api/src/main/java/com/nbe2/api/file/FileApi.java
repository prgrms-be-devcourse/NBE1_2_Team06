package com.nbe2.api.file;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.global.util.FileUtils;
import com.nbe2.domain.file.FileMetaData;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileApi {

    private final FileService fileService;

    @PostMapping
    public Response<Long> uploadFile(MultipartFile file) {
        FileMetaData fileMetaData = FileUtils.saveFile(file);

        return Response.success(1L);
    }
}
