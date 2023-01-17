package com.web.jar.services;

import com.web.jar.model.Post;

import java.util.List;

public interface PostService extends IService<Post,Long> {

    public List<Post> getAll();

    public Post getByName(String name);

    public List<Post> getPostsByUserTopic(Long idUser, Long idTopic);
}
