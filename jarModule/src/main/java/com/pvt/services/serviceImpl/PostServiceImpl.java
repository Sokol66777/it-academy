package com.pvt.services.serviceImpl;

import com.pvt.dao.PostDAO;
import com.pvt.model.Post;
import com.pvt.repository.PostRepository;
import com.pvt.services.BaseService;
import com.pvt.services.PostService;
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
