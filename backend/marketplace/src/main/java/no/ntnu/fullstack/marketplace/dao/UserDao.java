//package no.ntnu.fullstack.marketplace.dao;
//
//import no.ntnu.fullstack.marketplace.model.User;
//import no.ntnu.fullstack.marketplace.model.UserInfoResponse;
//import no.ntnu.fullstack.marketplace.service.UserServiceImpl;
//import no.ntnu.fullstack.marketplace.service.UserService;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
////import javax.persistence.EntityManager;
////import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
//
///**
// * This is a DAO for accessing user data.
// */
//public class UserDao {
//
//    private static final String PERSISTENCE_UNIT_NAME = "userdatabase";
//
//    @Autowired
//    private UserService userService;
//
//
//
//
//    public static boolean checkUserCredentials(final String username, final String password) {
//        boolean result = true; //test, should assume false and set to true if user is found
//
//
//
//        return result;
//    }
//
////    public User getUser(final String userId){
////        User user = userService.getUserByUsername(userId);
////        return user;
////    }
//
//}
