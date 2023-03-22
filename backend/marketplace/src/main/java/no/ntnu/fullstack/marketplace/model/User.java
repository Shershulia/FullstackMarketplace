package no.ntnu.fullstack.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table (name = "USERDATA")
//@Table
public class User {
    @Id
//    @Column (name = "id")
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column (name = "username", nullable = false, unique = true)
    private String username;
    @Column (name = "password", nullable = false)
    private String password;
    @Column (name = "name")
    private String name;
    @Column (name = "phone")
    private String phone;
    @Column (name = "email")
    private String email;

//    public User(String username, String password, String name, String phone, String email) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.name = null;
        this.phone = null;
        this.email = null;
    }

    public User() {

    }

    public String getId() {
        return username;
    }

    public void setId(String id) {
        this.username = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
//                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
