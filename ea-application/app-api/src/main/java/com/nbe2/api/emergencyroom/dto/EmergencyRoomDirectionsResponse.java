package com.nbe2.api.emergencyroom.dto;

import java.util.List;

import lombok.Builder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbe2.domain.emergencyroom.EmergencyRoomDirectionsInfo;

@Builder
public record EmergencyRoomDirectionsResponse(
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

    public static EmergencyRoomDirectionsResponse to(EmergencyRoomDirectionsInfo e) {
        return EmergencyRoomDirectionsResponse.builder()
                .code(e.code())
                .message(e.message())
                .currentDateTime(e.currentDateTime())
                .route(convertorRoute(e.route()))
                .build();
    }

    private static EmergencyRoomDirectionsResponse.Route convertorRoute(
            EmergencyRoomDirectionsInfo.Route route) {
        List<EmergencyRoomDirectionsResponse.Traoptimal> traoptimalList =
                route.traoptimal().stream()
                        .map(EmergencyRoomDirectionsResponse::convertorTraoptimal)
                        .toList();
        return EmergencyRoomDirectionsResponse.Route.builder().traoptimal(traoptimalList).build();
    }

    private static EmergencyRoomDirectionsResponse.Traoptimal convertorTraoptimal(
            EmergencyRoomDirectionsInfo.Traoptimal traoptimal) {
        return EmergencyRoomDirectionsResponse.Traoptimal.builder()
                .summary(convertoeSummary(traoptimal.summary()))
                .path(traoptimal.path())
                .section(convertorSections(traoptimal.section()))
                .guide(convertorGuides(traoptimal.guide()))
                .build();
    }

    private static EmergencyRoomDirectionsResponse.Summary convertoeSummary(
            EmergencyRoomDirectionsInfo.Summary summary) {
        return EmergencyRoomDirectionsResponse.Summary.builder()
                .start(convertorStart(summary.start()))
                .goal(convertorGoal(summary.goal()))
                .distance(summary.distance())
                .duration(summary.duration())
                .departureTime(summary.departureTime())
                .bbox(summary.bbox())
                .tollFare(summary.tollFare())
                .taxiFare(summary.taxiFare())
                .fuelPrice(summary.fuelPrice())
                .build();
    }

    private static List<EmergencyRoomDirectionsResponse.Section> convertorSections(
            List<EmergencyRoomDirectionsInfo.Section> section) {
        return section.stream().map(EmergencyRoomDirectionsResponse::convertorSection).toList();
    }

    private static EmergencyRoomDirectionsResponse.Section convertorSection(
            EmergencyRoomDirectionsInfo.Section section) {
        return EmergencyRoomDirectionsResponse.Section.builder()
                .pointIndex(section.pointIndex())
                .pointCount(section.pointCount())
                .distance(section.distance())
                .name(section.name())
                .congestion(section.congestion())
                .speed(section.speed())
                .build();
    }

    private static List<EmergencyRoomDirectionsResponse.Guide> convertorGuides(
            List<EmergencyRoomDirectionsInfo.Guide> guide) {
        return guide.stream().map(EmergencyRoomDirectionsResponse::convertorGuide).toList();
    }

    private static EmergencyRoomDirectionsResponse.Guide convertorGuide(
            EmergencyRoomDirectionsInfo.Guide guide) {
        return EmergencyRoomDirectionsResponse.Guide.builder()
                .type(guide.type())
                .instructions(guide.instructions())
                .distance(guide.distance())
                .duration(guide.duration())
                .build();
    }

    private static EmergencyRoomDirectionsResponse.Goal convertorGoal(
            EmergencyRoomDirectionsInfo.Goal goal) {
        return EmergencyRoomDirectionsResponse.Goal.builder()
                .location(goal.location())
                .dir(goal.dir())
                .build();
    }

    private static EmergencyRoomDirectionsResponse.Start convertorStart(
            EmergencyRoomDirectionsInfo.Start start) {
        return EmergencyRoomDirectionsResponse.Start.builder().location(start.location()).build();
    }
}
