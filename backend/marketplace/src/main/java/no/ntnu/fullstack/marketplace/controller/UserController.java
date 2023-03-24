package no.ntnu.fullstack.marketplace.controller;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.model.UserRequest;
import no.ntnu.fullstack.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


//@RequestMapping(value = "/user")
@EnableAutoConfiguration
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") Long id, @RequestHeader (name="Authorization") String token) {
        String tokenSubject = TokenController.getTokenSubject(token);
        System.out.println("Token subject: " + tokenSubject.toString() + " id: " + id.toString());

        if (!tokenSubject.equals(id.toString())) {
            //not acces to all the user data if not the same user
            User user = userService.getUserById(id);
            User copy = new User();
            copy.setId(id);
            copy.setUsername(user.getUsername());
            copy.setName(user.getName());

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
        copy.setId(id);
        copy.setEmail(user.getEmail());
        copy.setUsername(user.getUsername());
        copy.setName(user.getName());
        return copy;
    }
    @DeleteMapping("/user/delete/{id}")
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

    //creating post mapping that post the student detail in the database
    @PostMapping("/user/update")
    private Long saveUser(@RequestBody User user, @RequestHeader (name="Authorization") String token)
    {
        //forces user to update the profile with the same id as the token
        String tokenSubject = TokenController.getTokenSubject(token);
        user.setId(Long.parseLong(tokenSubject));

        System.out.println("Update user");
        System.out.println(user);
        userService.saveOrUpdate(user);
        return user.getId();
    }

    //create new user and return id
    @PostMapping("/user/register")
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
