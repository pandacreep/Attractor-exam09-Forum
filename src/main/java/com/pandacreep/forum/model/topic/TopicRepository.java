package com.pandacreep.forum.model.topic;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {
    Topic findByName(String name);
}
