package com.example.s3_practice.domain.feed.application;

import com.example.s3_practice.domain.feed.domain.Feed;
import com.example.s3_practice.domain.feed.domain.repository.FeedRepository;
import com.example.s3_practice.domain.feed.exception.FeedNotFoundException;
import com.example.s3_practice.domain.feed.presentation.dto.response.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryFeedService {
    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public FeedResponse query(Long id) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        return FeedResponse.builder()
                .title(feed.getTitle())
                .content(feed.getContent())
                .image(feed.getImageUrl())
                .build();
    }
}
