package com.pandacreep.forum.model.topic;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TopicDtoAddForm {

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotBlank(message = "description must not be blank")
    private String description;
}
