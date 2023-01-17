package com.web.forms;

import com.web.jar.model.Post;
import com.web.jar.model.Topic;
import lombok.*;
import org.hibernate.LazyInitializationException;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "posts")
@EqualsAndHashCode(exclude = "posts")
public class TopicForm {

    private long id;
    private String name;
    private Set<PostForm> posts = new HashSet<>();

    public TopicForm(Topic topic){

        this.id = topic.getID();
        this.name = topic.getName();
        try {
            for (Post post : topic.getPosts()) {
                PostForm postForm = new PostForm();
                postForm.setIdTopic(topic.getID());
                postForm.setId(post.getID());
                postForm.setIdUser(post.getUser().getID());
                postForm.setName(post.getName());
                postForm.setText(post.getText());
                this.posts.add(postForm);
            }
        }catch (LazyInitializationException e){
            this.posts = new HashSet<>();
        }
    }
}
