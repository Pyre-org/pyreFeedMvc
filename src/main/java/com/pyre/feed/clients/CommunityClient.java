package com.pyre.feed.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "community", path = "/community")
public interface CommunityClient {
    @GetMapping("/space/canWrite/{spaceId}")
    Boolean canWriteSpace(@RequestHeader("id") String userId, @RequestParam String spaceId);
}
