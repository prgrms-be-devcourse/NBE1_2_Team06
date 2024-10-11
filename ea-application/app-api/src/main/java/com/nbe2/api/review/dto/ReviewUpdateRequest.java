package com.nbe2.api.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.nbe2.domain.review.ReviewUpdateInfo;

public record ReviewUpdateRequest(
        @NotBlank(message = "내용을 입력해주세요.") String content,
        @NotNull(message = "응대속도 점수를 입력해주세요.")
                @Min(value = 0, message = "0점 미만의 점수를 입력할 수 없습니다.")
                @Max(value = 5, message = "5점 초과의 점수를 입력할 수 없습니다.")
                Double speedScore,
        @NotNull(message = "친절도 점수를 입력해주세요.")
                @Min(value = 0, message = "0점 미만의 점수를 입력할 수 없습니다.")
                @Max(value = 5, message = "5점 초과의 점수를 입력할 수 없습니다.")
                Double kindScore,
        @NotNull(message = "시설 만족도 점수를 입력해주세요.")
                @Min(value = 0, message = "0점 미만의 점수를 입력할 수 없습니다.")
                @Max(value = 5, message = "5점 초과의 점수를 입력할 수 없습니다.")
                Double facilityScore) {
    public ReviewUpdateInfo toReviewUpdateInfo() {
        return new ReviewUpdateInfo(content, speedScore, kindScore, facilityScore);
    }
}
