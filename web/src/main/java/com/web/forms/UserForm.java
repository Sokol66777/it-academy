package com.web.forms;


import com.web.jar.model.Post;
import com.web.jar.model.Topic;
import com.web.jar.model.User;
import lombok.*;
import org.hibernate.LazyInitializationException;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile fileData;
    private byte[] image;

    public UserForm(User user) {
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.id = user.getID();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.image= user.getImage();
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
