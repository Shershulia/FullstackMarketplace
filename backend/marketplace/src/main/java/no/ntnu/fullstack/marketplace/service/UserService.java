package no.ntnu.fullstack.marketplace.service;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUserById(String id)
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
        userRepository.save(user);
    }

    public void newUser(User user)
    {
        userRepository.save(user);
    }

    //deleting a specific record
    public void delete(String id)
    {
        userRepository.deleteById(id);
    }
}