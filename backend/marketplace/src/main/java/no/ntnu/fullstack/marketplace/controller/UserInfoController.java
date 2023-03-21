//package no.ntnu.fullstack.marketplace.controller;
//
//import no.ntnu.fullstack.marketplace.dao.UserDao;
//import no.ntnu.fullstack.marketplace.model.UserInfoResponse;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value = "/users")
//@EnableAutoConfiguration
//@CrossOrigin
//public class UserInfoController {
//
//    @GetMapping("/{userId}")
//    public UserInfoResponse getUser(@PathVariable("userId") String userId){
//        return UserDao.getUser(userId);
//    }
//
//}
