package userImpl;

import dao.AbstractJPADAO;
import dao.PostDAO;
import model.Post;

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
}
