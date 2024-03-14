package com.pyre.feed.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "auth-service", path = "/auth-service/user")
public interface UserClient {
    @GetMapping("/get/nickname/{userId}")
    ResponseEntity<String> getNickname(@PathVariable String userId);
}
