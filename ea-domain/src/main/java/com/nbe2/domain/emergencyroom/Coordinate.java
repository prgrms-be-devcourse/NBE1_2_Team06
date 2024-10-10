package com.nbe2.domain.emergencyroom;

import java.util.Objects;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class Coordinate {

    private Double longitude;
    private Double latitude;

    public static Coordinate of(Double longitude, Double latitude) {
        return new Coordinate(longitude, latitude);
    }

    public double distanceTo(Coordinate targetCoordinate) {
        final int EARTH_RADIUS_KM = 6371; // 지구의 평균 반지름 (킬로미터 단위)

        double lat1 = Math.toRadians(latitude);
        double lon1 = Math.toRadians(longitude);
        double lat2 = Math.toRadians(targetCoordinate.getLatitude());
        double lon2 = Math.toRadians(targetCoordinate.getLongitude());

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a =
                Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                        + Math.cos(lat1)
                                * Math.cos(lat2)
                                * Math.sin(deltaLon / 2)
                                * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Math.round(EARTH_RADIUS_KM * c * 100) / 100.0;
    }

    public String convertorLatitudeAndLongitude() {
        return longitude + "," + latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinate that)) {
            return false;
        }
        return Objects.equals(getLongitude(), that.getLongitude())
                && Objects.equals(getLatitude(), that.getLatitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitude(), getLatitude());
    }
}
