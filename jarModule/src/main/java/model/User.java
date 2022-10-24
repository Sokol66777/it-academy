package model;

import java.util.Objects;

public class User {
    private String username;
    private String email;
    private String password;
    private String role;
    private long ID;

    public User(){
    }

    public User(long ID, String username,String password, String email,  String role ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getID() == user.getID() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getRole(), user.getRole());
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
