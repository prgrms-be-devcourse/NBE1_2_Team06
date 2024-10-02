package com.nbe2.infra.naver.dto;

import java.util.List;

import lombok.Builder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbe2.domain.emergencyroom.EmergencyRoomDirectionsInfo;

@Builder
public record NaverDirectionsResponse(
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

    public static EmergencyRoomDirectionsInfo to(NaverDirectionsResponse n) {
        return EmergencyRoomDirectionsInfo.builder()
                .code(n.code)
                .message(n.message)
                .currentDateTime(n.currentDateTime)
                .route(convertorRoute(n.route))
                .build();
    }

    private static EmergencyRoomDirectionsInfo.Route convertorRoute(
            NaverDirectionsResponse.Route route) {
        List<EmergencyRoomDirectionsInfo.Traoptimal> traoptimalList =
                route.traoptimal.stream()
                        .map(NaverDirectionsResponse::convertorTraoptimal)
                        .toList();
        return EmergencyRoomDirectionsInfo.Route.builder().traoptimal(traoptimalList).build();
    }

    private static EmergencyRoomDirectionsInfo.Traoptimal convertorTraoptimal(
            NaverDirectionsResponse.Traoptimal traoptimal) {
        return EmergencyRoomDirectionsInfo.Traoptimal.builder()
                .summary(convertoeSummary(traoptimal.summary))
                .path(traoptimal.path)
                .section(convertorSections(traoptimal.section))
                .guide(convertorGuides(traoptimal.guide))
                .build();
    }

    private static EmergencyRoomDirectionsInfo.Summary convertoeSummary(
            NaverDirectionsResponse.Summary summary) {
        if (summary == null) {
            return null;
        }

        return EmergencyRoomDirectionsInfo.Summary.builder()
                .start(convertorStart(summary.start))
                .goal(convertorGoal(summary.goal))
                .distance(summary.distance)
                .duration(summary.duration)
                .departureTime(summary.departureTime)
                .bbox(summary.bbox)
                .tollFare(summary.tollFare)
                .taxiFare(summary.taxiFare)
                .fuelPrice(summary.fuelPrice)
                .build();
    }

    private static List<EmergencyRoomDirectionsInfo.Section> convertorSections(
            List<NaverDirectionsResponse.Section> section) {
        return section.stream().map(NaverDirectionsResponse::convertorSection).toList();
    }

    private static EmergencyRoomDirectionsInfo.Section convertorSection(
            NaverDirectionsResponse.Section section) {
        return EmergencyRoomDirectionsInfo.Section.builder()
                .pointIndex(section.pointIndex)
                .pointCount(section.pointCount)
                .distance(section.distance)
                .name(section.name)
                .congestion(section.congestion)
                .speed(section.speed)
                .build();
    }

    private static List<EmergencyRoomDirectionsInfo.Guide> convertorGuides(
            List<NaverDirectionsResponse.Guide> guide) {
        return guide.stream().map(NaverDirectionsResponse::convertorGuide).toList();
    }

    private static EmergencyRoomDirectionsInfo.Guide convertorGuide(
            NaverDirectionsResponse.Guide guide) {
        return EmergencyRoomDirectionsInfo.Guide.builder()
                .type(guide.type)
                .instructions(guide.instructions)
                .distance(guide.distance)
                .duration(guide.duration)
                .build();
    }

    private static EmergencyRoomDirectionsInfo.Goal convertorGoal(
            NaverDirectionsResponse.Goal goal) {
        return EmergencyRoomDirectionsInfo.Goal.builder()
                .location(goal.location)
                .dir(goal.dir)
                .build();
    }

    private static EmergencyRoomDirectionsInfo.Start convertorStart(
            NaverDirectionsResponse.Start start) {
        return EmergencyRoomDirectionsInfo.Start.builder().location(start.location).build();
    }
}
