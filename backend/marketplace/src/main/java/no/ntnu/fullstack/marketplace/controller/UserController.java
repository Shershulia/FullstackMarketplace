package no.ntnu.fullstack.marketplace.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

//    @GetMapping("/user")
//    private List<User> getAllUsers() {
//        System.out.println("Get all users");
//        return userService.getAllUser();
//    }

    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        return user;

    }

    @DeleteMapping("/user/{id}")
    private void deleteUser(@PathVariable("id") String id)
    {
        userService.delete(id);
    }
    //creating post mapping that post the student detail in the database
    @PostMapping("/user/update")
    private String saveUser(@RequestBody User user, @RequestHeader (name="Authorization") String token)
    {
        //TODO: get userid from token in header and check if it matches the id in the new user object

        String tokenSubject = TokenController.getTokenSubject(token);

        if (!tokenSubject.equals(user.getId())) {
            return "Token subject does not match user id";
        }

        System.out.println("Update user");
        System.out.println(user);
        userService.saveOrUpdate(user);
        return user.getId();
    }

    //create new user and return id
    @PostMapping("/user/new")
    private String newUser(@RequestBody User user)
    {
        try {
            userService.getUserByUsername(user.getUsername());
            return "Username already exists";
        } catch (Exception e) {
            System.out.println("Username is available");
        }
        System.out.println("New user");
        System.out.println(user);
        userService.newUser(user);
        return user.getId();
    }


}
