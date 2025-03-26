package com.example.s3_practice.infra.s3.exception;

import com.example.s3_practice.global.error.exception.DayException;
import com.example.s3_practice.global.error.exception.ErrorCode;

public class IncorrectUrlFormatException extends DayException {
    public static final IncorrectUrlFormatException EXCEPTION = new IncorrectUrlFormatException();

    private IncorrectUrlFormatException() {
        super(ErrorCode.INCORRECT_URL_FORMAT);
    }
}
