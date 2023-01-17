package com.web.jar.repository;

import com.web.jar.model.Topic;

import java.util.Optional;

public interface TopicRepository extends BaseRepository<Topic,Long>{

    Optional<Topic> findByName(String name);
}
