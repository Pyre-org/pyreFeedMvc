package com.pyre.feed.dto.response;

import com.pyre.feed.entity.Feed;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public record FeedGetListBySpaceResponse(
        Long total,
        List<FeedGetResponse> hits
) {
    public static FeedGetListBySpaceResponse makeDto(Page<Feed> feedPage, List<String> nicknames) {
        List<FeedGetResponse> feedGetResponseList = new ArrayList<>();
        for (Feed feed : feedPage.getContent()) {
            FeedGetResponse feedGetResponse = FeedGetResponse.makeDto(feed, nicknames.get(feedPage.getContent().indexOf(feed)));
            feedGetResponseList.add(feedGetResponse);
        }
        return new FeedGetListBySpaceResponse(feedPage.getTotalElements(), feedGetResponseList);
    }
}
