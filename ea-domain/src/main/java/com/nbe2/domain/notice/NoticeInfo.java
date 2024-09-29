package com.nbe2.domain.notice;

import java.util.Optional;

public record NoticeInfo(Optional<String> title, Optional<String> content, String hpId) {}
