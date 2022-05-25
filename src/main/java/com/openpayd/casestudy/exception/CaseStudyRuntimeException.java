package com.openpayd.casestudy.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CaseStudyRuntimeException extends RuntimeException {

    private final ErrorCode errorCode;

}
