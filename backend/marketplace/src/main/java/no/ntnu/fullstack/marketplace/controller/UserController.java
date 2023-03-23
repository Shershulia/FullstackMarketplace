package no.ntnu.fullstack.marketplace.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    private User getUser(@PathVariable("id") Long id, @RequestHeader (name="Authorization") String token) {
        String tokenSubject = TokenController.getTokenSubject(token);
        if (!tokenSubject.equals(id)) {
            return null;
        }
        User user = userService.getUserById(id);
        return user;
    }
//
//    @DeleteMapping("/user/{id}")
//    private void deleteUser(@PathVariable("id") Long id)
//    {
//        userService.delete(id);
//    }
    //creating post mapping that post the student detail in the database
    @PostMapping("/update")
    private Long saveUser(@RequestBody User user, @RequestHeader (name="Authorization") String token)
    {
        //TODO: get userid from token in header and check if it matches the id in the new user object

        String tokenSubject = TokenController.getTokenSubject(token);

        if (!tokenSubject.equals(user.getId())) {
            return 0L;
        }

        System.out.println("Update user");
        System.out.println(user);
        userService.saveOrUpdate(user);
        return user.getId();
    }

    //create new user and return id
    @PostMapping("/register")
    private Long newUser(@RequestBody String username, @RequestBody String email, @RequestBody String password, @RequestBody String firstName, @RequestBody String lastName, @RequestBody Integer age ){
        System.out.println("New user");
        User user = new User(username, email, password, firstName, lastName, age);

        User conflictingUser = userService.getUserByUsername(user.getUsername());

        System.out.println("Conflicting user: " + conflictingUser.toString());

        if (conflictingUser != null) {
            return null;
        }

        System.out.println("New user" + user.getUsername());
        userService.newUser(user);
        return user.getId();
    }


}
