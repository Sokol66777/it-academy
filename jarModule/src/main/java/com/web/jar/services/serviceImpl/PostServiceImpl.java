package com.web.jar.services.serviceImpl;

import com.web.jar.model.Post;
import com.web.jar.repository.PostRepository;
import com.web.jar.services.BaseService;
import com.web.jar.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends BaseService<Post,Long> implements PostService {

    @Autowired
    private PostRepository postRepositoryService;

    @Override
    public List<Post> getAll() {

        return postRepositoryService.findAll();
    }

    @Override
    public Post getByName(String name) {

        return postRepositoryService.findByName(name).orElse(null);
    }

    @Override
    public List<Post> getPostsByUserTopic(Long idUser, Long idTopic) {

        return postRepositoryService.getPostsByUserTopic(idUser,idTopic);
    }
}
