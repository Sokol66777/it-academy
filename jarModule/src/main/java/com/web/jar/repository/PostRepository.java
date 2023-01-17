package com.web.jar.repository;

import com.web.jar.model.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends BaseRepository<Post,Long>{

    @Query("select p from Post as p where p.user.id = ?1 and p.topic.id = ?2 ")
    public List<Post> getPostsByUserTopic(Long idUser, Long idTopic);

    Optional<Post> findByName(String name);
}
