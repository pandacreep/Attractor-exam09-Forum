package com.pandacreep.forum.model.user;

import com.pandacreep.forum.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document("users")
public class User implements UserDetails {

    private String id;
    @Id
    private String email;
    private String name;
    private String password;
    private String avatar;
    private int countPosts;

    @DBRef
    private List<Post> posts;

    public User from(UserDto user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("FULL"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
