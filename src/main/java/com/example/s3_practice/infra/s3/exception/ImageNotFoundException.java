package com.example.s3_practice.infra.s3.exception;

import com.example.s3_practice.global.error.exception.DayException;
import com.example.s3_practice.global.error.exception.ErrorCode;

public class ImageNotFoundException extends DayException {
    public static final ImageNotFoundException EXCEPTION = new ImageNotFoundException();

    private ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
