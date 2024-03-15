package com.pyre.feed.service.impl;

import com.pyre.feed.clients.CommunityClient;
import com.pyre.feed.clients.UserClient;
import com.pyre.feed.dto.request.FeedCreateRequest;
import com.pyre.feed.dto.request.FeedUpdateRequest;
import com.pyre.feed.dto.response.*;
import com.pyre.feed.entity.Feed;
import com.pyre.feed.exception.customexception.DataNotFoundException;
import com.pyre.feed.exception.customexception.PermissionDenyException;
import com.pyre.feed.repository.FeedRepository;
import com.pyre.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

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
        if (feedCreateRequest.url() == null || feedCreateRequest.url().equals("")) {
            throw new DataNotFoundException("URL이 없습니다.");
        }
        Feed feed = Feed.builder()
                .spaceId(feedCreateRequest.spaceId().toString())
                .userId(userId)
                .title((feedCreateRequest.title() != null &&
                        !feedCreateRequest.title().equals("")) ?
                        feedCreateRequest.title() : LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .description(feedCreateRequest.description())
                .url(feedCreateRequest.url())
                .build();
        feedRepository.insert(feed);

        return "성공적으로 피드가 업로드 되었습니다. ID: " + feed.getId();
    }
    @Transactional(readOnly = true)
    @Override
    public FeedGetResponse getFeed(String feedId, String userId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new DataNotFoundException("해당 피드를 찾을 수 없습니다."));
        if (!feed.getUserId().equals(userId)) {
            throw new PermissionDenyException("해당 피드를 조회할 권한이 없습니다.");
        }
        ResponseEntity<NicknameAndProfileImgResponse> nickname = userClient.getNicknameAndProfileImage(feed.getUserId());
        NicknameAndProfileImgResponse nicknameAndProfileImgResponse = nickname.getBody();
        return FeedGetResponse.makeDto(feed, nicknameAndProfileImgResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public FeedGetListBySpaceResponse getFeedList(String spaceId, String userId, int page, int size) {
        if (!communityClient.canWriteSpace(userId, spaceId)) {
            throw new PermissionDenyException("해당 스페이스의 피드를 볼 권한이 없습니다.");
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "cAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Feed> feeds = feedRepository.findAllBySpaceId(spaceId, pageable);
        List<ResponseEntity<NicknameAndProfileImgResponse>> nicknames = feeds.getContent().stream().map(
                feed -> userClient.getNicknameAndProfileImage(feed.getUserId())
        ).toList();
        List<NicknameAndProfileImgResponse> nicknameList = nicknames.stream().map(ResponseEntity::getBody).toList();

        FeedGetListBySpaceResponse feedGetListBySpaceResponse = FeedGetListBySpaceResponse.makeDto(feeds, nicknameList);
        return feedGetListBySpaceResponse;
    }
    @Transactional(readOnly = true)
    @Override
    public FeedGetMyListResponse getMyFeedList(String userId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "cAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Feed> feeds = feedRepository.findAllByUserId(userId, pageable);

        FeedGetMyListResponse feedGetMyListResponse = FeedGetMyListResponse.makeDto(feeds);
        return feedGetMyListResponse;
    }
    @Transactional(readOnly = true)
    @Override
    public FeedGetListBySpaceResponse getFeedListByOther(String userId, int page, int size) {
        List<String> spceIds = communityClient.canReadSpaces(userId).getBody().stream().map(UUID::toString).toList();
        Sort sort = Sort.by(Sort.Direction.DESC, "cAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Feed> feeds = feedRepository.findAllBySpaceIdIn(spceIds, pageable);
        List<ResponseEntity<NicknameAndProfileImgResponse>> nicknames = feeds.getContent().stream().map(
                feed -> userClient.getNicknameAndProfileImage(feed.getUserId())
        ).toList();
        List<NicknameAndProfileImgResponse> nicknameList = nicknames.stream().map(ResponseEntity::getBody).toList();
        FeedGetListBySpaceResponse feedGetListBySpaceResponse = FeedGetListBySpaceResponse.makeDto(feeds, nicknameList);
        return feedGetListBySpaceResponse;
    }

    @Transactional
    @Override
    public String deleteFeed(String feedId, String userId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new DataNotFoundException("해당 피드를 찾을 수 없습니다."));
        if (!feed.getUserId().equals(userId)) {
            throw new PermissionDenyException("해당 피드를 삭제할 권한이 없습니다.");
        }
        feedRepository.deleteById(feedId);
        return "성공적으로 피드가 삭제되었습니다.";
    }
    @Transactional
    @Override
    public FeedGetMyResponse editFeed(String userId, FeedUpdateRequest feedUpdateRequest) {
        Feed feed = feedRepository.findById(feedUpdateRequest.feedId()).orElseThrow(() -> new DataNotFoundException("해당 피드를 찾을 수 없습니다."));
        if (!feed.getUserId().equals(userId)) {
            throw new PermissionDenyException("해당 피드를 수정할 권한이 없습니다.");
        }
        feed.updateFeed(
                feedUpdateRequest.title(),
                feedUpdateRequest.description()
        );
        feedRepository.save(feed);
        return FeedGetMyResponse.makeDto(feed);
    }

}
