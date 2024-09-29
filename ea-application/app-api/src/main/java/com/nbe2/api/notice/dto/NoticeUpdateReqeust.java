package com.nbe2.api.notice.dto;

import com.nbe2.domain.notice.NoticeInfo;
import com.nbe2.domain.notice.NoticeUpdateInfo;
import java.util.Optional;

public record NoticeUpdateReqeust(
		String title,
		String content
) {
	public NoticeUpdateInfo toNoticeUpdateInfo() {
		return new NoticeUpdateInfo(Optional.ofNullable(title), Optional.ofNullable(content));
	}
}
