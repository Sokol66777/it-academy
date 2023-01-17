package com.pvt.repository;

import com.pvt.model.Topic;

import java.util.Optional;

public interface TopicRepository extends BaseRepository<Topic,Long>{

    Optional<Topic> findByName(String name);
}
