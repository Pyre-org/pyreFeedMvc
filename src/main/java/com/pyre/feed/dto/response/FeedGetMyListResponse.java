package com.pyre.feed.dto.response;

import com.pyre.feed.entity.Feed;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public record FeedGetMyListResponse(
        Long total,
        List<FeedGetMyResponse> hits
) {
    public static FeedGetMyListResponse makeDto(Page<Feed> feeds) {
        List<FeedGetMyResponse> hits = new ArrayList<>();
        for (Feed feed : feeds.getContent()) {
            hits.add(FeedGetMyResponse.makeDto(feed));
        }
        return new FeedGetMyListResponse(feeds.getTotalElements(), hits);
    }
}
