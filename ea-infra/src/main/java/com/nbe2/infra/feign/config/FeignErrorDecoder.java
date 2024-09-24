package com.nbe2.infra.feign.config;

import com.nbe2.infra.feign.exception.OtherServerBadRequestException;
import com.nbe2.infra.feign.exception.OtherServerExpiredTokenException;
import com.nbe2.infra.feign.exception.OtherServerForbiddenException;
import com.nbe2.infra.feign.exception.OtherServerUnauthorizedException;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400) {
            switch (response.status()) {
                case 401:
                    throw OtherServerUnauthorizedException.EXCEPTION;
                case 403:
                    throw OtherServerForbiddenException.EXCEPTION;
                case 419:
                    throw OtherServerExpiredTokenException.EXCEPTION;
                default:
                    throw OtherServerBadRequestException.EXCEPTION;
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
