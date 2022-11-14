package dao;

import model.Post;

import java.util.List;

public interface PostDAO extends DAO<Post>{

    public List<Post> getAll();

    public Post getByName(String name);

    public List<Post> getPostsByUserTopic(long idUser, long idTopic);

}
