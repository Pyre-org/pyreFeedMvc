package com.pyre.feed.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "community", path = "/community")
public interface CommunityClient {
    @GetMapping("/space/canWrite/{spaceId}")
    Boolean canWriteSpace(String userId, String spaceId);
}
