package com.pyre.feed.dto.response;

import com.pyre.feed.entity.Feed;

import java.time.format.DateTimeFormatter;

public record FeedGetMyResponse(
        String id,
        String userId,
        String spaceId,
        String title,
        String description,
        String imageUrl,
        String cAt)
{
    public static FeedGetMyResponse makeDto(Feed feed) {
        return new FeedGetMyResponse(feed.getId(),
                feed.getUserId(),
                feed.getSpaceId(),
                feed.getTitle(),
                feed.getDescription(),
                feed.getUrl(),
                feed.getCAt().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
