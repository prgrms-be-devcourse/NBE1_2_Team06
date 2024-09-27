package com.nbe2.infra.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenApiResponse1<T>(@JsonProperty("response") Response<T> response) {

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

            public record Items<T>(@JsonProperty("item") T item) {}
        }
    }

    public T getItems() {
        return response.body.items.item;
    }
}
