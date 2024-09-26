package com.nbe2.domain.user.exception;

import com.nbe2.common.exception.DomainException;

public class MedicalLicenseRequiredException extends DomainException {

    public static final DomainException EXCEPTION = new MedicalLicenseRequiredException();

    private MedicalLicenseRequiredException() {
        super(UserErrorCode.MEDICAL_LICENSE_REQUIRED);
    }
}
