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
    private String title;
    private String description;
    private String spaceId;
    private String url;
    @CreatedDate
    private LocalDateTime cAt;
    @Builder
    public Feed(
            String userId,
            String spaceId,
            String url,
            String title,
            String description
    ) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.spaceId = spaceId;
        this.url = url;
        this.cAt = LocalDateTime.now();
    }
}
