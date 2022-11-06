package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({@NamedQuery(name = "User.getUserByUsername",query = "select u from User AS u where u.username = :username"),
               @NamedQuery(name = "User.getUserByEmail", query = "select u from User as u where u.email = :email"),
               @NamedQuery(name = "User.getAllUsers", query = "select u from User as u")})
@Entity
@Table(name="user")

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "name",unique = true,nullable = false)
    private String username;
    @Column(name="email",unique = true,nullable = false)
    private String email;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="role",nullable = false)
    private String role;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set <Post> posts=new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "User_Topic",
            joinColumns = {@JoinColumn(name = "user_ID")},
            inverseJoinColumns = {@JoinColumn(name = "topic_ID")}
    )
    private Set<Topic> topics;

    @Override
    public String toString() {
        return ID+","+ username+',' + password+ ',' + email + ',' + role   ;
    }
}
