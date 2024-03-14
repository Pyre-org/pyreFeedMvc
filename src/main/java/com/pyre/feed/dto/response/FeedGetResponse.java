package com.pyre.feed.dto.response;

import com.pyre.feed.entity.Feed;

import java.time.format.DateTimeFormatter;

public record FeedGetResponse(
        String id,
        String userId,
        String nickname,
        String spaceId,
        String imageUrl,
        String cAt)
{
    public static FeedGetResponse makeDto(Feed feed) {
        return new FeedGetResponse(
                feed.getId(),
                feed.getUserId(),
                feed.getNickname(),
                feed.getSpaceId(),
                feed.getUrl(),
                feed.getCAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}