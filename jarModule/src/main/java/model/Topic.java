package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topic")
@NamedQueries({@NamedQuery(name = "Topic.getAllTopics", query = "select t from Topic as t"),
               @NamedQuery(name = "Topic.getTopicByName",query = "select t from Topic as t where t.name = :name")})
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(name = "name",nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    private Set<Post> posts;

    @ManyToMany(mappedBy = "topics")
    private Set<User> users;
}
