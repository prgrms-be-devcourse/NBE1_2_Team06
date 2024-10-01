package com.nbe2.domain.emergencyroom;

import java.util.List;

import lombok.Builder;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
public record EmergencyRoomDirectionsInfo(
        @JsonProperty("code") Integer code,
        @JsonProperty("message") String message,
        @JsonProperty("currentDateTime") String currentDateTime,
        @JsonProperty("route") Route route) {
    @Builder
    public record Route(
            @JsonProperty("trafast") Option trafast,
            @JsonProperty("tracomfort") Option tracomfort,
            @JsonProperty("traoptimal") Option traoptimal,
            @JsonProperty("traavoidtoll") Option traavoidtoll,
            @JsonProperty("traavoidcaronly") Option traavoidcaronly) {}

    @Builder
    public record Option(
            @JsonProperty("summary") Summary summary,
            @JsonProperty("path") List<Path> path,
            @JsonProperty("section") List<Section> section,
            @JsonProperty("guide") List<Guide> guide) {}

    @Builder
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

    @Builder
    public record Start(@JsonProperty("location") List<Double> location) {}

    @Builder
    public record Goal(
            @JsonProperty("location") List<Double> location, @JsonProperty("dir") Integer dir) {}

    @Builder
    public record Path(
            @JsonProperty("pointIndex") Integer pointIndex,
            @JsonProperty("coordinates") List<Double> coordinates) {}

    @Builder
    public record Section(
            @JsonProperty("pointIndex") Integer pointIndex,
            @JsonProperty("pointCount") Integer pointCount,
            @JsonProperty("distance") Integer distance,
            @JsonProperty("name") String name,
            @JsonProperty("congestion") Integer congestion,
            @JsonProperty("speed") Integer speed) {}

    @Builder
    public record Guide(
            @JsonProperty("pointIndex") Integer pointIndex,
            @JsonProperty("type") Integer type,
            @JsonProperty("instructions") String instructions,
            @JsonProperty("distance") Integer distance,
            @JsonProperty("duration") Integer duration) {}
}
