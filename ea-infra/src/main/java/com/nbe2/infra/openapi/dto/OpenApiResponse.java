package com.nbe2.infra.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nbe2.infra.openapi.config.ItemDeserializer;

public record OpenApiResponse<T>(@JsonProperty("response") Response<T> response) {

    public record Response<T>(
            @JsonProperty("header") Header header, @JsonProperty("body") Body<T> body) {

        public record Header(
                @JsonProperty("resultCode") String resultCode,
                @JsonProperty("resultMsg") String resultMessage) {}

        public record Body<T>(
                @JsonProperty("items") @JsonDeserialize(using = ItemDeserializer.class)
                        Items<T> items,
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
