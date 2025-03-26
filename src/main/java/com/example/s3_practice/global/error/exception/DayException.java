package com.example.s3_practice.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DayException extends RuntimeException{
    private final ErrorCode errorCode;





}
