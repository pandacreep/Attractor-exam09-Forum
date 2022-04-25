package com.pandacreep.forum.model.topic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicDto {
    private String id;
    private String name;
    private String description;
    private String date;
    private int postCount;
    private String user;

    public static TopicDto from(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .name(topic.getName())
                .description(topic.getDescription())
                .date(topic.getDate().toString())
                .postCount(topic.getPosts().size())
                .user(topic.getUser().getName())
                .build();
    }
}
