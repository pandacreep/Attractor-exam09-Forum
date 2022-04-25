package com.pandacreep.forum.model.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private String id;
    private String text;
    private String dateTime;
    private String user;
    private String userAvatar;
    private int userPostCount;
    private String topic;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .text(post.getText())
                .dateTime(post.getDateTime().toString())
                .user(post.getUser().getName())
                .userAvatar(post.getUser().getAvatar())
                .userPostCount(post.getUser().getCountPosts())
                .topic(post.getTopic().getName())
                .build();
    }
}
