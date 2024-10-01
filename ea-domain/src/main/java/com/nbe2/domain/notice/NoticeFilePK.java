package com.nbe2.domain.notice;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class NoticeFilePK implements Serializable {
    private Long fileMetaDataId;
    private Long noticeId;
}
