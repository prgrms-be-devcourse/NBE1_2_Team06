package com.nbe2.domain.notice;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class NoticeFile {
    /*

    	@Id
    	@OneToOne(fetch = FetchType.EAGER)
    	private File file;
    */

    // 오류 나서 임시로 해놨습니다
    @Id private Long fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;
}
