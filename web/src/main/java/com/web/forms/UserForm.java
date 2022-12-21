package com.web.forms;


import com.pvt.model.Post;
import com.pvt.model.Topic;
import com.pvt.model.User;
import jakarta.persistence.SecondaryTable;
import lombok.*;
import org.hibernate.LazyInitializationException;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"topics"})
@ToString(exclude = {"topics"})
public class UserForm {

    private long id;
    private String username;
    private String newUsername;
    private String password;
    private String email;
    private String role;
    private String confirmedPassword;
    private String newPassword;
    private String newEmail;
    private Set<TopicForm> topics = new HashSet<>();

    public UserForm(User user) {
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.id = user.getID();
        this.email = user.getEmail();
        this.role = user.getRole();
        try {
            for (Topic topic : user.getTopics()) {
                TopicForm topicForm = new TopicForm();
                topicForm.setId(topic.getID());
                topicForm.setName(topic.getName());
                try {
                    for (Post post : topic.getPosts()) {
                        PostForm postForm = new PostForm();
                        postForm.setId(post.getID());
                        postForm.setName(post.getName());
                        postForm.setText(post.getText());
                        postForm.setIdUser(user.getID());
                        postForm.setIdTopic(topic.getID());
                        topicForm.getPosts().add(postForm);
                    }
                }catch (LazyInitializationException e){
                    topicForm.setPosts(new HashSet<>());
                }
                this.topics.add(topicForm);
            }
        }catch (LazyInitializationException e){
            this.topics = new HashSet<>();
        }
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", role=" + role  ;
    }
}
