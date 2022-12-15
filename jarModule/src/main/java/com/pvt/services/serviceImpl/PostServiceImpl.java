package com.pvt.services.serviceImpl;

import com.pvt.dao.PostDAO;
import com.pvt.model.Post;
import com.pvt.services.BaseService;
import com.pvt.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends BaseService<Post> implements PostService {

    @Autowired
    private PostDAO postDAOService;

    @Override
    public List<Post> getAll() {

        return postDAOService.getAll();
    }

    @Override
    public Post getByName(String name) {

        return postDAOService.getByName(name);
    }

    @Override
    public List<Post> getPostsByUserTopic(long idUser, long idTopic) {

        return postDAOService.getPostsByUserTopic(idUser,idTopic);
    }
}
