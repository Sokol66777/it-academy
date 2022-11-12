package dao;

import model.Post;

import java.util.List;
import java.util.Set;

public interface PostDAO extends DAO<Post>{

    public Set<Post> getAll();

    public Post getByName(String name);

    public List<Post> getPostsByUserTopic(long idUser, long idTopic);

}
