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

//@RequestMapping(value = "/user")
@EnableAutoConfiguration
@RestController
@CrossOrigin
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") Long id, @RequestHeader(name="Authorization") String token) {
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
    @GetMapping("/user/pub/{id}")
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

    //create new user and return id
    @PostMapping("/user/register")
    private Long newUser(@RequestBody UserRequest userRequest) {
        LOGGER.debug("Register new user: {}", userRequest);

        if (userService.getUserByUsername(userRequest.username()) != null) {
            LOGGER.warn("Username already exists");
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User(userRequest.username(), userRequest.email(), userRequest.password(),
                userRequest.name(), userRequest.lastname(), userRequest.age());

        LOGGER.debug("New user: {}", user);
        userService.saveOrUpdate(user);
        Long id = userService.getUserByUsername(user.getUsername()).getId();
        LOGGER.debug("New user id: {}", id);
        return id;
    }
}
