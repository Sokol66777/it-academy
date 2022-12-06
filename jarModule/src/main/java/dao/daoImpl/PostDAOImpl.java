package dao.daoImpl;

import dao.BaseDAO;
import dao.PostDAO;
import jakarta.persistence.TypedQuery;
import model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAOImpl extends BaseDAO<Post> implements PostDAO {

    public PostDAOImpl(){
        super();
        clazz= Post.class;
    }

    @Override
    public List<Post> getAll() {

        TypedQuery<Post> namedQuery = entityManager.createNamedQuery("Post.getAllPosts", Post.class);
        List<Post> posts = namedQuery.getResultList();

        return posts;
    }

    @Override
    public Post getByName(String name) {

        TypedQuery<Post> namedQuery = entityManager.createNamedQuery("Post.getPostByName", Post.class).
                setParameter("name",name);
        Post post = namedQuery.getSingleResult();

        return post;
    }

    @Override
    public List<Post> getPostsByUserTopic(long idUser, long idTopic) {

        TypedQuery<Post> namedQuery = entityManager.createNamedQuery("Post.getByUserTopic", Post.class).
                setParameter("idTopic",idTopic).
                setParameter("idUser",idUser);
        List<Post>posts = namedQuery.getResultList();

        return posts;
    }


}
