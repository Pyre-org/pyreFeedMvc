package com.pyre.feed.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record FeedCreateRequest(
        @Schema(description = "스페이스 아이디", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID spaceId,
        @Schema(description = "S3 URL", example = "https://dqgtky3fkqa5j.cloudfront.net/123e4567-e89b-12d3-a456-426614174000")
        String url
) {
}

