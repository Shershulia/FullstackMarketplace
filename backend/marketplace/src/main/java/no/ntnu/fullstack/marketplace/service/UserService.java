package no.ntnu.fullstack.marketplace.service;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;


    //salt for bcrypt hashing TODO: move to config file or env variable
    private static String salt = ""; //TODO: implement salt

    //getting all user records
    public List<User> getAllUser()
    {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }
    //getting a specific record
    public User getUserById(Long id)
    {
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username)
    {
        List<User> users = getAllUser();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void saveOrUpdate(User user)
    {
        //hash password with bcrypt
        user.setPassword(this.hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    public void newUser(User user)
    {
        //hash password with bcrypt
        user.setPassword(this.hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    //deleting a specific record
    public void delete(Long id)
    {
        userRepository.deleteById(id);
    }

    public static String hashPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedPassword) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e){
            System.out.println("Error hashing password");
            return null;
        }
    }
}

