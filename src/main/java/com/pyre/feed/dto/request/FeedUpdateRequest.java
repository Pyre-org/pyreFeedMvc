package com.pyre.feed.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FeedUpdateRequest(
        @Schema(description = "피드 아이디", example = "asdasf-qweqw-czxc")
        @NotBlank(message = "피드 아이디를 입력해주세요.")
        String feedId,
        @Schema(description = "피드 제목", example = "피드 제목")
        String title,
        @Schema(description = "피드 설명", example = "피드 설명")
        String description
) {
}
