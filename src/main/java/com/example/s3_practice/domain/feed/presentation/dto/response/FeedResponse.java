package com.example.s3_practice.domain.feed.presentation.dto.response;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record FeedResponse(
        String title,
        String content,
        String image
) {
}
