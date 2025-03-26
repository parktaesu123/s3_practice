package com.example.s3_practice.infra.s3.exception;

import com.example.s3_practice.global.error.exception.DayException;
import com.example.s3_practice.global.error.exception.ErrorCode;

public class DeleteImageFailedException extends DayException {
    public static final DeleteImageFailedException EXCEPTION = new DeleteImageFailedException();

    private DeleteImageFailedException() {
        super(ErrorCode.DELETE_IMAGE_FAILED);
    }
}
