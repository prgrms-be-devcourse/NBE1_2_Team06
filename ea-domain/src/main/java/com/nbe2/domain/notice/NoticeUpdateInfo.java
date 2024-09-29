package com.nbe2.domain.notice;

import java.util.Optional;
import javax.swing.text.html.Option;

public record NoticeUpdateInfo(
		Optional<String> title,
		Optional<String> content
) {

}
