package com.pandacreep.forum.model.topic;

import com.pandacreep.forum.model.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public void addTopic(TopicDtoAddForm form, Principal principal) {
        var user = userRepository.findByEmail(principal.getName()).get();
        Topic topic = Topic.builder()
                .id(UUID.randomUUID().toString())
                .name(form.getName())
                .description(form.getDescription())
                .date(LocalDate.now())
                .user(user)
                .posts(new ArrayList<>())
                .build();
        topicRepository.save(topic);
    }

    public List<TopicDto> findAll() {
        List<Topic> topics = topicRepository.findAll();
        topics.sort(Comparator.comparing(Topic::getDate).reversed());
        List<TopicDto> topicDto = topics.stream()
                .map(TopicDto::from)
                .collect(Collectors.toList());
        return topicDto;
    }

    public Page<TopicDto> findAllPage(Pageable pageable) {
        var topics = topicRepository.findAll(pageable)
                .map(TopicDto::from);
        return topics;
    }

    public Topic findById(String id) {
        Topic topic = topicRepository.findById(id).get();
        return topic;
    }
}
