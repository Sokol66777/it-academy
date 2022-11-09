package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.io.Serializable;
import java.util.HashSet;
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
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Set<Post> posts = new HashSet<>();

    @ManyToMany(mappedBy = "topics", cascade = {CascadeType.PERSIST})
    private Set<User> users = new HashSet<>();

    @Override
    public String toString(){
        return name;
    }
}
