package com.example.s3_practice.domain.feed.presentation.dto.response;

import lombok.Builder;

@Builder
public record FeedResponse(
        String title,
        String content
) {
}
