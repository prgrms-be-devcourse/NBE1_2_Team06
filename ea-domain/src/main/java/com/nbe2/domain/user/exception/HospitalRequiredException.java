package com.nbe2.domain.user.exception;

import com.nbe2.common.exception.DomainException;

public class HospitalRequiredException extends DomainException {

    public static final DomainException EXCEPTION = new HospitalRequiredException();

    private HospitalRequiredException() {
        super(UserErrorCode.HOSPITAL_REQUIRED);
    }
}
