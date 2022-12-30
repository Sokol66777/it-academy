package com.web.forms;

import com.pvt.model.Post;
import com.pvt.model.Topic;
import com.pvt.model.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PostForm {

    private long id;
    private String name;
    private String text;
    private long idUser;
    private long idTopic;

    public PostForm(Post post){

        this.id = post.getID();
        this.name = post.getName();
        this.text = post.getText();
        this.idUser = post.getUser().getID();
        this.idTopic = post.getTopic().getID();
    }
}
