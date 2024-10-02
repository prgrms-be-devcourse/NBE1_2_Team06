package com.nbe2.domain.user;

import static com.nbe2.domain.user.QMedicalPersonInfo.medicalPersonInfo;
import static com.nbe2.domain.user.QUser.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserProfileWithLicense> findPageByApprovalStatus(
            ApprovalStatus approvalStatus, Pageable pageable) {
        List<UserProfileWithLicense> content =
                queryFactory
                        .select(
                                Projections.constructor(
                                        UserProfileWithLicense.class,
                                        user.id,
                                        user.name,
                                        user.email,
                                        medicalPersonInfo.license.id))
                        .from(user)
                        .join(medicalPersonInfo)
                        .on(medicalPersonInfo.user.id.eq(user.id))
                        .where(equalApprovalStatus(approvalStatus))
                        .orderBy(user.createdAt.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();
        JPAQuery<Long> countQuery =
                queryFactory
                        .select(user.id.count())
                        .from(user)
                        .where(equalApprovalStatus(approvalStatus));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression equalApprovalStatus(ApprovalStatus approvalStatus) {
        if (approvalStatus == null) {
            return null;
        }

        return user.approvalStatus.eq(approvalStatus);
    }
}
