package no.ntnu.fullstack.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table (name = "USERDATA")
//@Table
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    @Column (name = "id", columnDefinition = "serial", nullable = false)
    @Column (name = "id")
    private Long id;

    @Column (name = "username", nullable = false, unique = true)
    private String username;
    @Column (name = "email")
    private String email;
    @Column (name = "password", nullable = false)
    private String password;

    @Column (name = "name")
    private String name;

    @Column (name = "lastname")
    private String lastname;
    @Column (name = "age")
    private Integer age;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = null;
        this.name = null;
        this.lastname = null;
        this.age = null;
    }

    public User(String username,String email,String password,String name,String lastname,Integer age){
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
