package com.pandacreep.forum.model.topic;

import com.pandacreep.forum.model.post.Post;
import com.pandacreep.forum.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Document("topics")
public class Topic {
    @Id
    private String id;

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotBlank(message = "description must not be blank")
    private String description;

    private LocalDate date;
    private int postCount;

    @DBRef
    private User user;

    @DBRef
    private List<Post> posts;
}
