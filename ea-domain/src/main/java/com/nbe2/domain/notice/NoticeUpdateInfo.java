package com.nbe2.domain.notice;

import java.util.Optional;

public record NoticeUpdateInfo(Optional<String> title, Optional<String> content) {}
