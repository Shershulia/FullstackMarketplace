package no.ntnu.fullstack.marketplace.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "userdata")
public class User {
    //make it compliant with the UserInfoResponse class and the database CREATE TABLE IF NOT EXISTS userdatabase (id INT NOT NULL AUTO_INCREMENT, username VARCHAR(50) NOT NULL UNIQUE, name VARCHAR(100) NOT NULL, phone VARCHAR(20), email VARCHAR(100) NOT NULL, password VARCHAR(100) NOT NULL, PRIMARY KEY (id));

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String username;
    private String name;
    private String phone;
    private String email;
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
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

    public void setPhone(){
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(){
        this.password = password;
    }
}


