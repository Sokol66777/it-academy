package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({@NamedQuery(name = "User.getUserByUsername",query = "select u from User AS u where u.username = :username"),
               @NamedQuery(name = "User.getUserByEmail", query = "select u from User as u where u.email = :email"),
               @NamedQuery(name = "User.getAllUsers", query = "select u from User as u")})
@Entity
@Table(name="user")

public class User {
    @Column(name = "name")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;
    @Id
    private long ID;

    @Override
    public String toString() {
        return ID+","+ username+',' + password+ ',' + email + ',' + role   ;
    }
}
