package com.pyre.feed.service;

import com.pyre.feed.dto.request.FeedCreateRequest;
import com.pyre.feed.dto.request.FeedUpdateRequest;
import com.pyre.feed.dto.response.FeedGetListBySpaceResponse;
import com.pyre.feed.dto.response.FeedGetMyListResponse;
import com.pyre.feed.dto.response.FeedGetMyResponse;
import com.pyre.feed.dto.response.FeedGetResponse;
import org.springframework.transaction.annotation.Transactional;

public interface FeedService {
    @Transactional
    String uploadObject(FeedCreateRequest feedCreateRequest, String userId);
    @Transactional(readOnly = true)
    FeedGetResponse getFeed(String feedId, String userId);
    @Transactional(readOnly = true)
    FeedGetListBySpaceResponse getFeedList(String spaceId, String userId, int page, int size);
    @Transactional(readOnly = true)
    FeedGetMyListResponse getMyFeedList(String userId, int page, int size);
    @Transactional(readOnly = true)
    FeedGetListBySpaceResponse getFeedListByOther(String userId, int page, int size);
    @Transactional
    String deleteFeed(String feedId, String userId);
    @Transactional
    FeedGetMyResponse editFeed(String userId, FeedUpdateRequest feedUpdateRequest);
}
