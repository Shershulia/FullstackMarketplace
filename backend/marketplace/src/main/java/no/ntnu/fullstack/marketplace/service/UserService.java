package no.ntnu.fullstack.marketplace.service;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
/**
 * UserService class for handling user data from the database
 */
@Service
public class UserService
{
    /**
     * UserRepository object for handling database queries
     * @see UserRepository
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Salt for bcrypt hashing should be moved to config file or env variable in production
     */
    //salt for bcrypt hashing TODO: move to config file or env variable
    private static String salt = ""; //TODO: implement salt

    /**
     * Method for getting all users from the database and returning them as a list
     * @return List of all users
     */
    //getting all user records
    public List<User> getAllUser()
    {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    /**
     * Method for getting a specific user from the database by id
     * @param id id of the user to get
     * @return User object with the specified id
     */
    //getting a specific record
    public User getUserById(Long id)
    {
        return userRepository.findById(id).get();
    }

    /**
     * Method for getting a specific user from the database by username
     * @param username username of the user to get
     * @return User object with the specified username, null if no user with that username exists
     */
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

    /**
     * Method for creating or updating a user in the database
     * @param user User object to save or update in the database, if the user already exists in the database it will be updated, hashes the password with bcrypt before saving
     * @return User object with the specified email, null if no user with that email exists
     */
    public void saveOrUpdate(User user)
    {
        //hash password with bcrypt
        user.setPassword(this.hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Method for creating a new user in the database
     * @param user User object to save in the database, hashes the password with bcrypt before saving
     */
    public void newUser(User user)
    {
        //hash password with bcrypt
        user.setPassword(this.hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Method for deleting a user from the database by id
     * @param id id of the user to delete
     */
    //deleting a specific record
    public void delete(Long id)
    {
        userRepository.deleteById(id);
    }

    /**
     * Method for hashing a password with bcrypt and returning the hashed password
     * @param password password to hash
     * @return hashed password
     */
    public static String hashPassword(String password) {
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
