package com.example.s3_practice.domain.feed.exception;

import com.example.s3_practice.global.error.exception.DayException;
import com.example.s3_practice.global.error.exception.ErrorCode;

public class FeedNotFoundException extends DayException {
    public static final FeedNotFoundException EXCEPTION = new FeedNotFoundException();

    public FeedNotFoundException() {
        super(ErrorCode.FEED_NOT_FOUND);
    }
}
