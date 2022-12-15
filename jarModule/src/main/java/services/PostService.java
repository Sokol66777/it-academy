package services;

import model.Post;

import java.util.List;

public interface PostService extends IService<Post> {

    public List<Post> getAll();

    public Post getByName(String name);

    public List<Post> getPostsByUserTopic(long idUser, long idTopic);
}
