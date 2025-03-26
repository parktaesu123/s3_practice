package com.example.s3_practice.infra.s3.exception;

import com.example.s3_practice.global.error.exception.DayException;
import com.example.s3_practice.global.error.exception.ErrorCode;

public class FileFailedException extends DayException {
    public static final FileFailedException EXCEPTION = new FileFailedException();

    public FileFailedException() {
        super(ErrorCode.FAIL_FILE);
    }
}
