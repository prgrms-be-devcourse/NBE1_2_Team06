package com.nbe2.domain.notice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import com.nbe2.domain.file.FileMetaData;

@Entity
@Builder
@IdClass(NoticeFilePK.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice_files")
public class NoticeFile {

    @Id
    @Column(name = "file_id")
    private Long fileMetaDataId;

    @Id
    @Column(name = "notice_id")
    private Long noticeId;

    public static NoticeFile of(FileMetaData fileMetaData, Notice notice) {
        return NoticeFile.builder()
                .fileMetaDataId(fileMetaData.getId())
                .noticeId(notice.getNoticeId())
                .build();
    }
}
