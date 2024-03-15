package com.pyre.feed.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "community", path = "/community")
public interface CommunityClient {
    @GetMapping("/space/canWrite/{spaceId}")
    Boolean canWriteSpace(@RequestHeader("id") String userId, @RequestParam String spaceId);
    @GetMapping("/space/canReadFeedSpaces")
    ResponseEntity<List<UUID>> canReadSpaces(@RequestHeader("id") String userId);
}
