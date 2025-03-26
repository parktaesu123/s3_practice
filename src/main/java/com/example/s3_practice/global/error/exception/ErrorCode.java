package com.example.s3_practice.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum ErrorCode {

    // general
    BAD_REQUEST(400, "프론트 탓이 확실하다.."),
    INTERNAL_SERVER_ERROR(500, "서버 탓일 수도 있고.."),

    // key
    INVALID_KEY(401, "잘못된 key입니다."),

    //feed
    FEED_NOT_FOUND(404, "Feed Not Found."),
    CANNOT_DELETE_FEED(403, "Cannot Delete Feed."),
    CANNOT_MODIFY_FEED(403, "Cannot Modify Feed."),
    CANNOT_CREATE_FEED(403, "Cannot Create Feed"),

    //like
    LIKE_EXIST(409, "Like Exist."),
    CANNOT_DELETE_LIKE(409, "Cannot Delete Like."),

    //image
    FAIL_FILE(500, "파일 업로드 실패"),
    IMAGE_NOT_FOUND(404, "파일이 없어용"),
    DELETE_IMAGE_FAILED(500, "이미지 삭제 실패"),
    INCORRECT_URL_FORMAT(400, "잘못된 URL형식");

    private final int statusCode;
    private final String message;
}
