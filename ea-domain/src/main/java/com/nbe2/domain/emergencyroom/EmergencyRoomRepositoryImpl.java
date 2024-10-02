package com.nbe2.domain.emergencyroom;

import static com.nbe2.domain.emergencyroom.EmergencyRoom.*;
import static com.nbe2.domain.emergencyroom.QEmergencyRoom.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@RequiredArgsConstructor
public class EmergencyRoomRepositoryImpl implements EmergencyRoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<EmergencyRoomMapInfo> findByCoordinateAndDistance(
            Coordinate coordinate, double distance) {
        NumberTemplate<Double> calculatedDistance = getCalculatedDistance(coordinate);

        return queryFactory
                .select(
                        new QEmergencyRoomMapInfo(
                                emergencyRoom.id, // 필요한 필드만 선택
                                emergencyRoom.hpId,
                                emergencyRoom.hospitalName,
                                emergencyRoom.address,
                                emergencyRoom.simpleMap,
                                emergencyRoom.location,
                                calculatedDistance))
                .from(emergencyRoom)
                .where(calculatedDistance.loe(distance))
                .orderBy(calculatedDistance.asc())
                .fetch();
    }

    private static NumberTemplate<Double> getCalculatedDistance(Coordinate coordinate) {
        return Expressions.numberTemplate(
                Double.class,
                "ST_Distance_Sphere({0}, {1})",
                emergencyRoom.location,
                coordinateToPoint(coordinate));
    }
}
