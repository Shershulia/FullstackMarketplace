package no.ntnu.fullstack.marketplace.controller;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.model.UserRequest;
import no.ntnu.fullstack.marketplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user related requests
 * Contains methods for creating, updating, deleting and getting users
 * Handles requests to /user and /user/{id} endpoints and handles user requests
 */
@RequestMapping(value = "/user")
@EnableAutoConfiguration
@RestController
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
        LOGGER.debug("Token subject: {} id: {}", tokenSubject, id);

        if (!tokenSubject.equals(id.toString())) {
            //not access to all the user data if not the same user
            User user = userService.getUserById(id);
            User copy = new User();
            copy.setId(user.getId());
            copy.setUsername(user.getUsername());
            copy.setName(user.getName());
            copy.setEmail(user.getEmail());
            return copy;
        }

        User user = userService.getUserById(id);
        return user;
    }

    /**
     * Get user by id without token check, used for public info like username first name and email address
     * @param id user id
     * @return user object with only public info like username first name and email address
     */
    @GetMapping("/pub/{id}")
    @CrossOrigin
    private User getUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        //only return public info
        User copy = new User();
        copy.setId(user.getId());
        copy.setUsername(user.getUsername());
        copy.setName(user.getName());
        copy.setEmail(user.getEmail());

        return copy;
    }

    @DeleteMapping("/user/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteUser(@RequestBody User user, @RequestHeader(name="Authorization") String token) {
        String tokenSubject = TokenController.getTokenSubject(token);
        LOGGER.debug("Token subject: {} id: {}", tokenSubject, user.getId());

        if (!tokenSubject.equals(user.getId().toString())) {
            LOGGER.warn("Access denied, wrong credentials....");
            throw new IllegalArgumentException("Access denied, wrong credentials....");
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
    @PostMapping("/user/update")
    private Long saveUser(@RequestBody User user, @RequestHeader(name="Authorization") String token) {
        //forces user to update the profile with the same id as the token
        String tokenSubject = TokenController.getTokenSubject(token);
        user.setId(Long.parseLong(tokenSubject));

        LOGGER.debug("Update user: {}", user);
        userService.saveOrUpdate(user);
        return user.getId();
    }

    /**
     * Create new user in database
     * @param userRequest user object to create in database, user id is generated automatically by database
     * @return id of created user
     */
    //create new user and return id
    @PostMapping("/user/register")
    private Long newUser(@RequestBody UserRequest userRequest) {
        LOGGER.debug("Register new user: {}", userRequest);

        if (userService.getUserByUsername(userRequest.username()) != null) {
            LOGGER.warn("Username already exists");
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User(userRequest.username(), userRequest.email(), userRequest.password(),
                userRequest.name(), userRequest.lastname(), userRequest.age(), userRequest.permission());

        LOGGER.debug("New user: {}", user);
        userService.saveOrUpdate(user);
        Long id = userService.getUserByUsername(user.getUsername()).getId();
        LOGGER.debug("New user id: {}", id);
        return id;
    }
}
