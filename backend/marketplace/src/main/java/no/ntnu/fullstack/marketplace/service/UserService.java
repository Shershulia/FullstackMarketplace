package no.ntnu.fullstack.marketplace.service;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

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


    public static String hashPassword(String password) {
        System.out.println("Hashing password");
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}