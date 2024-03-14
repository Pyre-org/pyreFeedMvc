package com.pyre.feed.repository;

import com.pyre.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FeedRepository extends MongoRepository<Feed, String> {
}
