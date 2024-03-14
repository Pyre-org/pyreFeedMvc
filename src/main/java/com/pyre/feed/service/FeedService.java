package com.pyre.feed.service;

import com.pyre.feed.dto.request.FeedCreateRequest;
import org.springframework.transaction.annotation.Transactional;

public interface FeedService {
    @Transactional
    String uploadObject(FeedCreateRequest feedCreateRequest, String userId);
}
