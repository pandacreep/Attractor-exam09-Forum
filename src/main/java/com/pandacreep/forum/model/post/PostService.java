package com.pandacreep.forum.model.post;

import com.pandacreep.forum.model.topic.Topic;
import com.pandacreep.forum.model.topic.TopicRepository;
import com.pandacreep.forum.model.user.User;
import com.pandacreep.forum.model.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public void addPost(PostDtoAddPost form, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).get();
        Topic topic = topicRepository.findById(form.getTopicId()).get();
        Post post = Post.builder()
                .id(UUID.randomUUID().toString())
                .text(form.getText())
                .dateTime(LocalDateTime.now())
                .user(user)
                .topic(topic)
                .build();
        postRepository.save(post);

        List<Post> posts = topic.getPosts();
        posts.add(post);
        topicRepository.save(topic);

        user.setCountPosts(user.getCountPosts() + 1);
        userRepository.save(user);
    }

    public Page<PostDto> findByTopicPage(Topic topic, Pageable pageable) {
        Pageable pageableSort = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(), Sort.by("dateTime").ascending());
        var posts = postRepository.findAllByTopic(topic, pageableSort)
                .map(PostDto::from);

        return posts;
    }

    public void addPostRest(String id, String text, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).get();
        Topic topic = topicRepository.findById(id).get();
        Post post = Post.builder()
                .id(UUID.randomUUID().toString())
                .text(text)
                .dateTime(LocalDateTime.now())
                .user(user)
                .topic(topic)
                .build();
        postRepository.save(post);

        List<Post> posts = topic.getPosts();
        posts.add(post);
        topicRepository.save(topic);

        user.setCountPosts(user.getCountPosts() + 1);
        userRepository.save(user);
    }
}
