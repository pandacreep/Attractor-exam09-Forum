package com.pandacreep.forum.model.post;

import com.pandacreep.forum.model.topic.Topic;
import com.pandacreep.forum.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("posts")
public class Post {
    @Id
    private String id;
    private String text;
    private LocalDateTime dateTime;

    @DBRef
    private User user;

    @DBRef
    private Topic topic;
}
