package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({@NamedQuery(name = "Post.getAllPosts", query = "select p from post as p"),
               @NamedQuery(name = "Post.getByName", query = "select p from post as p where p.name = :name"),
               @NamedQuery(name = "Post.getByUserTopic", query = "select p from post as p where p.topic.id = :idTopic and p.user.id = :idUser")})
@Table(name = "post",uniqueConstraints = @UniqueConstraint(name = "user_topic_post", columnNames = {"ID","user_ID", "topic_ID"}))
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
