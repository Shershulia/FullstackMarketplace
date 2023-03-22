package no.ntnu.fullstack.marketplace.controller;

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




    @GetMapping("/user")
    private List<User> getAllUsers() {
        System.out.println("Get all users");
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") String id) {
        return userService.getUserById(id);

    }

    @DeleteMapping("/user/{id}")
    private void deleteUser(@PathVariable("id") String id)
    {
        userService.delete(id);
    }
    //creating post mapping that post the student detail in the database
    @PostMapping("/user")
    private String saveUser(@RequestBody User user)
    {
        userService.saveOrUpdate(user);
        return user.getId();
    }


}
