package com.example.s3_practice.domain.feed.domain.repository;

import com.example.s3_practice.domain.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
