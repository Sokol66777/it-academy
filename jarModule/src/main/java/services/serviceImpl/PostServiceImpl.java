package services.serviceImpl;

import dao.PostDAO;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.BaseService;
import services.PostService;

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
