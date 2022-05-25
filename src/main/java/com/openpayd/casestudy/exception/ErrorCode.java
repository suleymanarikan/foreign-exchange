package com.openpayd.casestudy.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    AN_ERROR_OCCUR		                    ("ERR-1", "Unexpected error occur", HttpStatus.INTERNAL_SERVER_ERROR),
    FEIGN_CLIENT_CALL_ERROR		            ("ERR-2", "Unexpected error occur when feign client call", HttpStatus.NOT_FOUND),
    MISSING_TRANSACTION_ID_AND_DATE		    ("ERR-101", "At least one of the transaction id or date required", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
