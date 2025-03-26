package com.example.s3_practice.domain.feed.application;

import com.example.s3_practice.domain.feed.domain.Feed;
import com.example.s3_practice.domain.feed.domain.repository.FeedRepository;
import com.example.s3_practice.domain.feed.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteFeedService {
    private final FeedRepository feedRepository;

    @Transactional
    public void delete(Long id) {
        Feed feed = feedRepository.findById(id)
                        .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        feedRepository.delete(feed);
    }
}
