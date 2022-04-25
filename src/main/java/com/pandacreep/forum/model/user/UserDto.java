package com.pandacreep.forum.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserDto {
    private String id;
    private String email;
    private String name;
    private String password;
    private String avatar;
    private int countPosts;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .countPosts(user.getCountPosts())
                .build();
    }
}
