package com.example.s3_practice.domain.feed.application;

import com.example.s3_practice.domain.feed.domain.Feed;
import com.example.s3_practice.domain.feed.domain.repository.FeedRepository;
import com.example.s3_practice.domain.feed.presentation.dto.request.FeedRequest;
import com.example.s3_practice.infra.s3.exception.FileFailedException;
import com.example.s3_practice.infra.s3.service.S3AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CreateFeedService {
    private final FeedRepository feedRepository;
    private final S3AsyncService s3AsyncService;

    @Transactional
    public CompletableFuture<Void> createFeed(FeedRequest request, MultipartFile image) {

        CompletableFuture<String> previewImageUrlFuture;

        if (image != null && !image.isEmpty()) {
            try {
                previewImageUrlFuture = s3AsyncService.uploadAsync(image)
                        .thenApply(s3AsyncService::getUrl);
            } catch (IOException e) {
                throw FileFailedException.EXCEPTION;
            }
        } else {
            previewImageUrlFuture = CompletableFuture.completedFuture(null);
        }

        return previewImageUrlFuture.thenAccept(imageUrl -> {
            feedRepository.save(Feed.builder()
                    .title(request.getTitle())
                    .content(request.getContent())
                    .imageUrl(imageUrl) // 이미지 URL 포함
                    .build());
        });
    }
}