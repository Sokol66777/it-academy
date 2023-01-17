package com.web.jar.repository;

import com.web.jar.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends BaseRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("select u from User u left join fetch u.topics where u.ID = ?1")
    User getUserByIdWithTopic(long id);
}
