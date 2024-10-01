package com.nbe2.infra.naver.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverDirectionsResponse(
        @JsonProperty("code") Integer code,
        @JsonProperty("message") String message,
        @JsonProperty("currentDateTime") String currentDateTime,
        @JsonProperty("route") Route route) {
    public record Route(
            @JsonProperty("summary") Summary summary,
            @JsonProperty("path") List<Double[]> path,
            @JsonProperty("section") List<Section> section,
            @JsonProperty("guide") List<Guide> guide) {}

    public record Summary(
            @JsonProperty("start") Start start,
            @JsonProperty("goal") Goal goal,
            @JsonProperty("distance") Integer distance,
            @JsonProperty("duration") Integer duration,
            @JsonProperty("departureTime") String departureTime,
            @JsonProperty("bbox") List<Double> bbox,
            @JsonProperty("tollFare") Integer tollFare,
            @JsonProperty("taxiFare") Integer taxiFare,
            @JsonProperty("fuelPrice") Integer fuelPrice) {}

    public record Start(@JsonProperty("location") List<Double> location) {}

    public record Goal(
            @JsonProperty("location") List<Double> location, @JsonProperty("dir") Integer dir) {}

    public record Section(
            @JsonProperty("pointIndex") Integer pointIndex,
            @JsonProperty("pointCount") Integer pointCount,
            @JsonProperty("distance") Integer distance,
            @JsonProperty("name") String name,
            @JsonProperty("congestion") Integer congestion,
            @JsonProperty("speed") Integer speed) {}

    public record Guide(
            @JsonProperty("pointIndex") Integer pointIndex,
            @JsonProperty("type") Integer type,
            @JsonProperty("instructions") String instructions,
            @JsonProperty("distance") Integer distance,
            @JsonProperty("duration") Integer duration) {}
}
