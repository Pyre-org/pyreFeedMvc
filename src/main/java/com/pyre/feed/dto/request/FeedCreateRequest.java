package com.pyre.feed.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record FeedCreateRequest(
        @Schema(description = "스페이스 아이디", example = "123e4567-e89b-12d3-a456-426614174000")
        @NotNull(message = "스페이스 아이디를 입력해주세요.")
        UUID spaceId,
        @Schema(description = "S3 URL", example = "https://dqgtky3fkqa5j.cloudfront.net/123e4567-e89b-12d3-a456-426614174000")
        @Pattern(regexp = "^(https://dqgtky3fkqa5j.cloudfront.net/).*", message = "URL 형식이 올바르지 않습니다.")
        @NotBlank(message = "S3 URL을 입력해주세요.")
        String url,
        @Schema(description = "피드 제목", example = "피드 제목")

        String title,
        @Schema(description = "피드 설명", example = "피드 설명")
        String description
) {
}

