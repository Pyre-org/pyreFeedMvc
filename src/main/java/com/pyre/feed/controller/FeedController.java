package com.pyre.feed.controller;

import com.pyre.feed.dto.request.FeedCreateRequest;
import com.pyre.feed.dto.response.FeedGetListBySpaceResponse;
import com.pyre.feed.dto.response.FeedGetResponse;
import com.pyre.feed.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/feed")
@Tag(name = "Feed", description = "Feed API 구성")
@Validated
public class FeedController {
    private final FeedService feedService;
    @PostMapping("/upload")
    @Operation(description = "피드를 업로드하는 엔드포인트")
    @Parameters({
            @Parameter(description = "액세스 토큰 아이디", name = "id", required = true, in = ParameterIn.HEADER)
    })
    public ResponseEntity<String> upload(
            @RequestHeader("id") String userId,
            @RequestBody @Valid FeedCreateRequest feedCreateRequest
            ) {
        return ResponseEntity.ok(feedService.uploadObject(feedCreateRequest, userId));
    }
    @GetMapping("/list/{spaceId}")
    @Operation(description = "스페이스의 피드 리스트 최신 순으로 조회하는 엔드포인트")
    @Parameters({
            @Parameter(description = "액세스 토큰 아이디", name = "id", required = true, in = ParameterIn.HEADER),
            @Parameter(description = "페이지 번호", name = "page", required = false, in = ParameterIn.QUERY),
            @Parameter(description = "페이지 사이즈", name = "size", required = false, in = ParameterIn.QUERY),
            @Parameter(description = "스페이스 아이디", name = "spaceId", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<FeedGetListBySpaceResponse> getFeedList(
            @RequestHeader("id") String userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @PathVariable String spaceId
    ) {
        return ResponseEntity.ok(feedService.getFeedList(spaceId, userId, page, size));
    }
}
