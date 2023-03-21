package no.ntnu.fullstack.marketplace.controller;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    private List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);

    }

    @DeleteMapping("/user/{id}")
    private void deleteUser(@PathVariable("id") Long id)
    {
        userService.delete(id);
    }
    //creating post mapping that post the student detail in the database
    @PostMapping("/user")
    private Long saveUser(@RequestBody User user)
    {
        userService.saveOrUpdate(user);
        return user.getId();
    }


}
