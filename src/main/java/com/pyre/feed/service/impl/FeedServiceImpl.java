package com.pyre.feed.service.impl;

import com.pyre.feed.clients.CommunityClient;
import com.pyre.feed.clients.UserClient;
import com.pyre.feed.dto.request.FeedCreateRequest;
import com.pyre.feed.dto.response.FeedGetListBySpaceResponse;
import com.pyre.feed.dto.response.FeedGetResponse;
import com.pyre.feed.entity.Feed;
import com.pyre.feed.exception.customexception.PermissionDenyException;
import com.pyre.feed.repository.FeedRepository;
import com.pyre.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {
    private final FeedRepository feedRepository;
    private final CommunityClient communityClient;
    private final UserClient userClient;
    @Transactional
    @Override
    public String uploadObject(FeedCreateRequest feedCreateRequest, String userId) {
        if (!communityClient.canWriteSpace(userId, feedCreateRequest.spaceId().toString())) {
            throw new PermissionDenyException("해당 스페이스에 피드를 올릴 권한이 없습니다.");
        }
        String nickname = userClient.getNickname(userId);
        Feed feed = Feed.builder()
                .spaceId(feedCreateRequest.spaceId().toString())
                .userId(userId)
                .url(feedCreateRequest.url())
                .nickname(nickname)
                .build();
        feedRepository.insert(feed);

        return "성공적으로 피드가 업로드 되었습니다. ID: " + feed.getId();
    }

    @Override
    public FeedGetListBySpaceResponse getFeedList(String spaceId, String userId, int page, int size) {
        if (!communityClient.canWriteSpace(userId, spaceId)) {
            throw new PermissionDenyException("해당 스페이스의 피드를 볼 권한이 없습니다.");
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "cAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Feed> feeds = feedRepository.findAllBySpaceId(spaceId, pageable);
        FeedGetListBySpaceResponse feedGetListBySpaceResponse = FeedGetListBySpaceResponse.makeDto(feeds);
        return feedGetListBySpaceResponse;
    }
}
