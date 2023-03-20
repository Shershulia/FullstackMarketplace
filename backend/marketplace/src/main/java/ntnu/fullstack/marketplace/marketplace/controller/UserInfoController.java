package ntnu.fullstack.marketplace.marketplace.controller;

import no.ntnu.idatt2105.tokenauth.demo.dao.MockDao;
import no.ntnu.idatt2105.tokenauth.demo.model.UserInfoResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@EnableAutoConfiguration
@CrossOrigin
public class UserInfoController {

    @GetMapping("/{userId}")
    public UserInfoResponse getUser(@PathVariable("userId") String userId){
        return new MockDao().getUser(userId);
    }

}
