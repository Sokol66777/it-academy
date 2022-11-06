package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private long ID;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "text",nullable = false)
    private String text;

    @ManyToOne()
    @JoinColumn(name = "user_ID")
    private User user;
}
