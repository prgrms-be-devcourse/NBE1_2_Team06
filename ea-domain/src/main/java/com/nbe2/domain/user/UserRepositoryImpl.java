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
    public Page<UserProfileWithLicense> findPageBySignupStatus(
            SignupStatus signupStatus, Pageable pageable) {
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
                        .where(equalSignupStatus(signupStatus))
                        .orderBy(user.createdAt.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();
        JPAQuery<Long> countQuery =
                queryFactory
                        .select(user.id.count())
                        .from(user)
                        .where(equalSignupStatus(signupStatus));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression equalSignupStatus(SignupStatus signupStatus) {
        if (signupStatus == null) {
            return null;
        }

        return user.signupStatus.eq(signupStatus);
    }
}
