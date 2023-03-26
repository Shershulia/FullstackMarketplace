package no.ntnu.fullstack.marketplace.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.model.UserRequest;
import no.ntnu.fullstack.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for user related requests
 * Contains methods for creating, updating, deleting and getting users
 * Handles requests to /user and /user/{id} endpoints and handles user requests
 */
@RequestMapping(value = "/user")
@EnableAutoConfiguration
@CrossOrigin
@RestController
public class UserController {
    /**
     * Service for user related requests
     * Contains methods for creating, updating, deleting and getting users
     */
    @Autowired
    UserService userService;

    /**
     * Get user by id from database and return user object as json
     * @param id id of user to get from database
     * @param token token from header to verify user credentials so that only the user can get their own personal data
     * @return user object
     */
    @GetMapping("/{id}")
    private User getUser(@PathVariable("id") Long id, @RequestHeader (name="Authorization") String token) {
        String tokenSubject = TokenController.getTokenSubject(token);
        System.out.println("Token subject: " + tokenSubject.toString() + " id: " + id.toString());

        if (!tokenSubject.equals(id.toString())) {
            System.out.println("Access denied, wrong credentials....");
            return null;
        }

        User user = userService.getUserById(id);
        return user;
    }

    /**
     * Delete user from database
     * @param user user object to delete from database
     * @param token token from header to verify user credentials so that only the user can delete their own personal data
     */
    @DeleteMapping("/delete/{id}")
    private void deleteUser(@RequestBody User user, @RequestHeader (name="Authorization") String token)
    {
        String tokenSubject = TokenController.getTokenSubject(token);
        System.out.println("Token subject: " + tokenSubject.toString() + " id: " + user.getId().toString());

        if (!tokenSubject.equals(user.getId().toString())) {
            System.out.println("Access denied, wrong credentials....");
            return;
        }

        userService.delete(user.getId());
    }

    /**
     * Update user in database with new user object
     * @param user new data to update user with
     * @param token token from header to verify user credentials so that only the user can update their own personal data
     *              user id is extracted from token and updates user with that id
     * @return id of updated user
     */
    //creating post mapping that post the student detail in the database
    @PostMapping("/update")
    private Long saveUser(@RequestBody User user, @RequestHeader (name="Authorization") String token)
    {
        //TODO: get userid from token in header and check if it matches the id in the new user object

        String tokenSubject = TokenController.getTokenSubject(token);
        user.setId(Long.parseLong(tokenSubject));

        System.out.println("Update user");
        System.out.println(user);
        userService.saveOrUpdate(user);
        return user.getId();
    }

    /**
     * Create new user in database
     * @param userRequest user object to create in database, user id is generated automatically by database
     * @return id of created user
     */
    //create new user and return id
    @PostMapping("/register")
    @CrossOrigin
    private Long newUser(@RequestBody UserRequest userRequest)
    {
        //TODO: check if username already exists
        System.out.println("Register new user");

        if (userService.getUserByUsername(userRequest.username()) != null) {
            System.out.println("Username already exists");
            return null;
        }

        User user = new User();
        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setName(userRequest.name());
        user.setLastname(userRequest.lastname());
        user.setAge(userRequest.age());

        System.out.println(user);
        userService.saveOrUpdate(user);
        Long id = userService.getUserByUsername(user.getUsername()).getId();
        System.out.println("New user id: " + id);
        return id;
    }



}
