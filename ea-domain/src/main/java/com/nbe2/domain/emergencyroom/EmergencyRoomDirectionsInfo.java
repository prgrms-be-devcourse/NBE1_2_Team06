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
    public record Route(@JsonProperty("traoptimal") List<Traoptimal> traoptimal) {}

    @Builder
    public record Traoptimal(
            @JsonProperty("summary") Summary summary,
            @JsonProperty("path") List<Double[]> path,
            @JsonProperty("section") List<Section> section,
            @JsonProperty("guide") List<Guide> guide) {}

    @Builder
    public record Summary(
            @JsonProperty("start") Start start,
            @JsonProperty("goal") Goal goal,
            @JsonProperty("distance") Integer distance,
            @JsonProperty("duration") Integer duration,
            @JsonProperty("departureTime") String departureTime,
            @JsonProperty("bbox") List<List<Double>> bbox,
            @JsonProperty("tollFare") Integer tollFare,
            @JsonProperty("taxiFare") Integer taxiFare,
            @JsonProperty("fuelPrice") Integer fuelPrice) {}

    @Builder
    public record Start(@JsonProperty("location") List<Double> location) {}

    @Builder
    public record Goal(
            @JsonProperty("location") List<Double> location, @JsonProperty("dir") Integer dir) {}

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
