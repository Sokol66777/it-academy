package userImpl;

import dao.AbstractJPADAO;
import dao.PostDAO;
import jakarta.persistence.TypedQuery;
import model.Post;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostDAOImpl extends AbstractJPADAO implements PostDAO {
    @Override
    public void delete(long id) {

        init();
        Post post = entityManager.find(Post.class,id);
        entityManager.remove(post);
        close();
    }

    @Override
    public void add(Post post)  {

        init();
        entityManager.persist(post);
        close();

    }

    @Override
    public void modify(Post post)  {

        init();
        entityManager.merge(post);
        close();

    }

    @Override
    public Post get(long id) {

        init();
        Post post = entityManager.find(Post.class,id);
        close();
        return post;
    }

    @Override
    public Set<Post> getAll() {

        init();
        TypedQuery<Post> namedQuery = entityManager.createNamedQuery("Post.getAllPosts", Post.class);
        List<Post> posts = namedQuery.getResultList();
        close();
        return new HashSet<>(posts);
    }

    @Override
    public Post getByName(String name) {

        init();
        TypedQuery<Post> namedQuery = entityManager.createNamedQuery("Post.getByName", Post.class).
                setParameter("name",name);
        Post post = namedQuery.getSingleResult();
        close();
        return post;
    }

    @Override
    public List<Post> getPostsByUserTopic(long idUser, long idTopic) {

        init();
        TypedQuery<Post> namedQuery = entityManager.createNamedQuery("Post.getByUserTopic", Post.class).
                setParameter("idTopic",idTopic).
                setParameter("idUser",idUser);
        List<Post>posts = namedQuery.getResultList();
        close();
        return posts;
    }
}
