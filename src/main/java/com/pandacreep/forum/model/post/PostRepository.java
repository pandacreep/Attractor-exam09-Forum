package com.pandacreep.forum.model.post;

import com.pandacreep.forum.model.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String>, PagingAndSortingRepository<Post, String> {
    Page<Post> findAllByTopic(Topic topic, Pageable pageable);
}
