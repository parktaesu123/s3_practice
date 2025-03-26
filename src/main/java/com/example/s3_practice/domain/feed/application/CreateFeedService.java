package com.example.s3_practice.domain.feed.application;

import com.example.s3_practice.domain.feed.domain.Feed;
import com.example.s3_practice.domain.feed.domain.repository.FeedRepository;
import com.example.s3_practice.domain.feed.presentation.dto.request.FeedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateFeedService {
    private final FeedRepository feedRepository;

    @Transactional
    public void createFeed(FeedRequest request) {

        feedRepository.save(Feed.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                .build());
    }
}
