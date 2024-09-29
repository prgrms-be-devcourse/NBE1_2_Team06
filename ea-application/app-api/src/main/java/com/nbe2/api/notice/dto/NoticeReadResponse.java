package com.nbe2.api.notice.dto;

import com.nbe2.domain.emergencyroom.EmergencyRoom;

public record NoticeReadResponse(
		Long emergencyRoomId,
		String title,
		String content
) {
	public
}
