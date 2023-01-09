package com.pvt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Lob
    @Column(name = "image")
    private byte[] image;


    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set <Post> posts=new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "User_Topic",
            joinColumns = {@JoinColumn(name = "user_ID")},
            inverseJoinColumns = {@JoinColumn(name = "topic_ID")}
    )
    private Set<Topic> topics=new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getID() == user.getID() && getUsername().equals(user.getUsername()) && getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) && getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail(), getPassword(), getRole(), getID());
    }

    @Override
    public String toString() {
        return ID+","+ username+',' + password+ ',' + email + ',' + role   ;
    }
}
