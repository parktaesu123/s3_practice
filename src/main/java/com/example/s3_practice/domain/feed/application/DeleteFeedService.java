package com.example.s3_practice.domain.feed.application;

import com.example.s3_practice.domain.feed.domain.Feed;
import com.example.s3_practice.domain.feed.domain.repository.FeedRepository;
import com.example.s3_practice.domain.feed.exception.FeedNotFoundException;
import com.example.s3_practice.infra.s3.service.S3AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class DeleteFeedService {
    private final FeedRepository feedRepository;
    private final S3AsyncService s3AsyncService;

    @Transactional
    public CompletableFuture<Void> delete(Long id, String imageUrl) {
        Feed feed = feedRepository.findById(id)
                        .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        CompletableFuture<Void> imageDeleteFuture = null;
        if (imageUrl != null && !imageUrl.isEmpty()) {
            imageDeleteFuture = s3AsyncService.deleteAsync(imageUrl);
        } else {
            imageDeleteFuture = CompletableFuture.completedFuture(null);
        }

        return imageDeleteFuture.thenRunAsync(() -> {
            feedRepository.delete(feed);
        });
    }
}
