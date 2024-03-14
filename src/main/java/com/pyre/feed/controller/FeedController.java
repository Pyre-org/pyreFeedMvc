package com.pyre.feed.controller;

import com.pyre.feed.dto.request.FeedCreateRequest;
import com.pyre.feed.service.FeedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/feed")
@Tag(name = "Feed", description = "Feed API 구성")
public class FeedController {
    private final FeedService feedService;
    @PostMapping("/upload/{spaceId}")
    public ResponseEntity<String> upload(
            @RequestHeader("id") String userId,
            @RequestBody FeedCreateRequest feedCreateRequest
            ) {
        return ResponseEntity.ok(feedService.uploadObject(feedCreateRequest, userId));
    }
}
