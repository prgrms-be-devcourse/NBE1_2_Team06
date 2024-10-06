package com.nbe2.domain.review;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.review.exception.ReviewNotFoundException;

@Component
@RequiredArgsConstructor
public class Validator {
    public Page<Review> validateRead(
            Optional<Page<Review>> reviewReadAll) { // 해당 아이디의 병원의 리뷰가 없을 때 EXCEPTION
        // @TODO 없을 때 예외가 안남.. 왜지?
        reviewReadAll.orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
        return reviewReadAll.get();
    }
}
