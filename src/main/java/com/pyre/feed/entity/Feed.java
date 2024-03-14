package com.pyre.feed.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "feed")
public class Feed {
    @Id
    private String id;
    private String userId;
    private String spaceId;
    private String url;
    @CreatedDate
    private LocalDateTime cAt;
    @Builder
    public Feed(
            String userId,
            String spaceId,
            String url
    ) {
        this.userId = userId;
        this.spaceId = spaceId;
        this.url = url;
        this.cAt = LocalDateTime.now();
    }
}
