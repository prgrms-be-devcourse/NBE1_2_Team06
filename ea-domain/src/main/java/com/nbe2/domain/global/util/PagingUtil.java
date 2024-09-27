package com.nbe2.domain.global.util;

import org.springframework.data.domain.PageRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.nbe2.common.dto.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagingUtil {

    public static PageRequest toPageRequest(Page page) {
        return PageRequest.of(page.page(), page.size());
    }
}
