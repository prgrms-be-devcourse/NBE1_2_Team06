package com.nbe2.domain.notice;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class NoticeFilePK implements Serializable {

    private Long fileMetaDataId;
    private Long noticeId;

    public NoticeFilePK(Long fileMetaDataId, Long noticeId) {
        this.fileMetaDataId = fileMetaDataId;
        this.noticeId = noticeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileMetaDataId, noticeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NoticeFilePK that = (NoticeFilePK) obj;
        return Objects.equals(fileMetaDataId, that.fileMetaDataId)
                && Objects.equals(noticeId, that.noticeId);
    }
}
