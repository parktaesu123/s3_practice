package com.example.s3_practice.domain.feed.presentation;

import com.example.s3_practice.domain.feed.application.CreateFeedService;
import com.example.s3_practice.domain.feed.application.DeleteFeedService;
import com.example.s3_practice.domain.feed.application.QueryFeedService;
import com.example.s3_practice.domain.feed.presentation.dto.request.FeedRequest;
import com.example.s3_practice.domain.feed.presentation.dto.response.FeedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final CreateFeedService createFeedService;
    private final QueryFeedService queryFeedService;
    private final DeleteFeedService deleteFeedService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFeed(@RequestBody @Valid FeedRequest request) {
        createFeedService.createFeed(request);
    }

    @GetMapping("/{id}")
    public FeedResponse getFeed(@PathVariable Long id) {
        return queryFeedService.query(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteFeedService.delete(id);
    }
}
