package no.ntnu.fullstack.marketplace.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
//import no.ntnu.fullstack.marketplace.dao.MockDao;
//import no.ntnu.fullstack.marketplace.dao.UserDao;
import no.ntnu.fullstack.marketplace.model.LoginRequest;
import no.ntnu.fullstack.marketplace.controller.UserController;
import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;

@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
@RestController
public class TokenController {


    @Autowired
    UserService userService;


    /**
     * This is the secret key used to sign the JWT token.
     * It should be a long random string.
     * In a real scenario, it should either be injected from the environment like the following line.
     */
    public static final String keyStr = "secretkey";
    //public static final String keyStr = System.getenv("JWT_SECRET_KEY");


    /**
     * This is the validity time  of the JWT token.
     */
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5); //really short validity for testing purpose

    /**
     * This method is used to create a JWT token and return it to the client.
     * The password is hashed before being compared with the stored password, because the stored password is hashed in the database.
     * @param loginRequest contains the username and password of the user trying to login to the system from the http request body
     * @return the JWT token as a string
     * @throws Exception if the username or password is wrong or if the JWT token cannot be created for some reason
     */
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(final @RequestBody LoginRequest loginRequest) throws Exception {

        //hash input password and compare with stored password
        String hashedPassword = userService.hashPassword(loginRequest.password());

        // if username and password are valid, issue an access token
        // note that subsequent requests need this token
        User user = userService.getUserByUsername(loginRequest.username());
        if (user != null) {
            if (user.getPassword().equals(hashedPassword)) {
                return generateToken(user.getId());
            }
        }

        System.out.println("Access denied, wrong credentials....");

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }

    /**
     * This method is used to create a JWT token, it takes the user id as a parameter and returns the JWT token as a string.
     * @param userId the id of the user
     * @return the JWT token as a string
     */
    public String generateToken(final Long userId) {
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
        final JWTVerifier verifier = JWT.require(hmac512).build();
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withIssuer("fullstack_marketplace_api")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }

//    /**
//     * This method is used to verify the JWT token and return the user id.
//     * @param token the JWT token
//     * @return the user id as a string if the token is valid
//     */
//    @GetMapping(value = "/verify")
//    @ResponseStatus(value = HttpStatus.OK)
//    public String verifyToken(final @RequestHeader("Authorization") String token) throws Exception {
//        // verify access token and return user id
//        if (token != null && token.startsWith("Bearer ")) { // Bearer is the default prefix for JWT
//            final String jwtToken = token.substring(7);
//            final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
//            final JWTVerifier verifier = JWT.require(hmac512).build();
//            verifier.verify(jwtToken);
//            return JWT.decode(jwtToken).getSubject();
//        }
//        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
//    }

    /**
     * gets the subject of the token (the user id) and returns it as a string
     * @param token the JWT token
     * @return the user id as a string if the token is valid
     */
    public static String getTokenSubject(final String token) {
        // verify access token and return user id
        if (token != null && token.startsWith("Bearer ")) { // Bearer is the default prefix for JWT
            final String jwtToken = token.substring(7);
            final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
            final JWTVerifier verifier = JWT.require(hmac512).build();
            verifier.verify(jwtToken);
            return JWT.decode(jwtToken).getSubject();
        }
        return null;
    }

}