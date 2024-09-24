package com.nbe2.infra.openapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenApiResponse<T>(@JsonProperty("response") Response<T> response) {

    public record Response<T>(
            @JsonProperty("header") Header header, @JsonProperty("body") Body<T> body) {

        public record Header(
                @JsonProperty("resultCode") String resultCode,
                @JsonProperty("resultMsg") String resultMessage) {}

        public record Body<T>(
                @JsonProperty("items") Items<T> items,
                @JsonProperty("numOfRows") int numOfRows,
                @JsonProperty("pageNo") int pageNo,
                @JsonProperty("totalCount") int totalCount) {

            public record Items<T>(@JsonProperty("item") List<T> item) {}
        }
    }

    public List<T> getItems() {
        return response.body.items.item;
    }
}
