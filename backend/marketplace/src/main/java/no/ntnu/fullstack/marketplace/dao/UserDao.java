//package no.ntnu.fullstack.marketplace.dao;
//
//import no.ntnu.fullstack.marketplace.model.User;
//import no.ntnu.fullstack.marketplace.model.UserInfoResponse;
//import no.ntnu.fullstack.marketplace.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * This is a mock DAO for testing purposes.
// */
//public class UserDao {
//
//    @Autowired
//    UserService userService;
//
//    public UserDao() {
//        addUser("user1", "password1");
//        addUser("user2", "password2");
//        addUser("user3", "password3");
//        addUser("ole1", "password1");
//    }
//
//
//    public void addUser(String username, String password) {
//        User user1 = new User(username, password);
//        userService.saveOrUpdate(user1);
//    }
//
//    public boolean checkUserCredentials(final String username, final String password) {
//        User user = userService.getUserByUsername(username);
//        if (user != null) {
//            if (user.getPassword().equals(password)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}