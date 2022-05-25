package com.openpayd.casestudy.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CaseStudyExceptionHandler {

    @ExceptionHandler(CaseStudyRuntimeException.class)
    public final ResponseEntity<Object> handleCaseStudyRuntimeException(CaseStudyRuntimeException ex) {
        log.error(ex.getErrorCode().getMessage());
        return new ResponseEntity<>(ex.getErrorCode().getCode(), ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ErrorCode.FEIGN_CLIENT_CALL_ERROR.getCode(), ErrorCode.FEIGN_CLIENT_CALL_ERROR.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ErrorCode.AN_ERROR_OCCUR.getCode(), ErrorCode.AN_ERROR_OCCUR.getHttpStatus());
    }
}