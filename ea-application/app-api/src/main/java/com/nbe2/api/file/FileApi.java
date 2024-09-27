package com.nbe2.api.file;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.nbe2.api.global.dto.Response;
import com.nbe2.api.global.util.FileUtils;
import com.nbe2.domain.file.FileMetaData;
import com.nbe2.domain.file.FileMetaDataService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileApi {

    private final FileMetaDataService fileMetaDataService;

    @PostMapping
    public Response<Long> uploadFile(MultipartFile file) {
        FileMetaData fileMetaData = FileUtils.saveFile(file);
        return Response.success(fileMetaDataService.save(fileMetaData));
    }

    @GetMapping("/{fileId}/download")
    public ResponseEntity<Resource> download(@PathVariable Long fileId) {
        FileMetaData fileMetaData = fileMetaDataService.getFileMetaData(fileId);
        File file = FileUtils.validate(fileMetaData.getPath());
        Resource resource = new FileSystemResource(file);

        String encoded =
                new String(
                        tradingFile.getFileName().getBytes(StandardCharsets.UTF_8),
                        StandardCharsets.ISO_8859_1);

        log.info("download file: {}", encoded);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + encoded + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))
                .body(resource);
    }
}
