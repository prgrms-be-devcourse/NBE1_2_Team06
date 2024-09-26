package com.nbe2.domain.emergencyroom;

public record Coordinate(Double longitude, Double latitude) {

    public static Coordinate of(Double longitude, Double latitude) {
        return new Coordinate(longitude, latitude);
    }
}
