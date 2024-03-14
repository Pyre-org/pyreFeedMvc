package com.pyre.feed.service;

import com.pyre.feed.dto.request.FeedCreateRequest;
import com.pyre.feed.dto.response.FeedGetListBySpaceResponse;
import com.pyre.feed.dto.response.FeedGetResponse;
import org.springframework.transaction.annotation.Transactional;

public interface FeedService {
    @Transactional
    String uploadObject(FeedCreateRequest feedCreateRequest, String userId);
    @Transactional(readOnly = true)
    FeedGetListBySpaceResponse getFeedList(String spaceId, String userId, int page, int size);
}
