package com.web.fasad;

import com.web.jar.exceptions.LogicException;
import com.web.jar.model.Post;
import com.web.jar.services.PostService;
import com.web.jar.services.TopicService;
import com.web.jar.services.UserService;
import com.web.forms.PostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostFasad {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    private Post buildPost(PostForm postForm){

        Post post = new Post();
        post.setID(postForm.getId());
        post.setText(postForm.getText());
        post.setName(postForm.getName());
        post.setUser(userService.get(postForm.getIdUser()));
        post.setTopic(topicService.get(postForm.getIdTopic()));
        return post;
    }

    public List<PostForm> getPostsByUserTopic(long idUser, long idTopic){

        List<Post> posts = postService.getPostsByUserTopic(idUser,idTopic);
        List<PostForm> postForms = new ArrayList<>();

        for(Post post:posts){
            postForms.add(new PostForm(post));
        }

        return postForms;
    }

    public void deletePost(long idPost){

        postService.delete(idPost);
    }

    public void addPost(PostForm postForm) throws LogicException {

        Post post = buildPost(postForm);
        postService.add(post);
    }

    public PostForm get(long idPost){
        return new PostForm(postService.get(idPost));
    }

    public void updatePost(PostForm postForm) throws LogicException {

        Post post = buildPost(postForm);
        postService.modify(post);
    }
}
