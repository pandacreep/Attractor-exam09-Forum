package com.pandacreep.forum.model.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostDtoAddPost {
    @NotBlank(message = "comment must not be blank")
    private final String text;
    private final String topicId;
}
