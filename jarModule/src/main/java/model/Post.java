package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({@NamedQuery(name = "Post.getAllPosts", query = "select p from Post as p"),
               @NamedQuery(name = "Post.getPostByName", query = "select p from Post as p where p.name = :name"),
               @NamedQuery(name = "Post.getByUserTopic", query = "select p from Post as p where p.topic.id = :idTopic and p.user.id = :idUser")})
@Entity
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "text",nullable = false)
    private String text;

    @ManyToOne()
    @JoinColumn(name = "user_ID")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "topic_ID")
    private Topic topic;

}
