package com.nbe2.domain.notice;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.nbe2.domain.file.FileMetaData;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "notice_files")
public class NoticeFile {

    @EmbeddedId private NoticeFilePK noticeFilePK = new NoticeFilePK();

    @MapsId("fileMetaDataId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileMetaData fileMetaData;

    @MapsId("noticeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    public static NoticeFile of(FileMetaData fileMetaData, Notice notice) {
        NoticeFilePK noticeFilePK = NoticeFilePK.of(fileMetaData.getId(), notice.getNoticeId());
        return NoticeFile.builder()
                .noticeFilePK(noticeFilePK)
                .fileMetaData(fileMetaData)
                .notice(notice)
                .build();
    }
}
