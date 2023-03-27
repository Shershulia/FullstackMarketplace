package no.ntnu.fullstack.marketplace.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import no.ntnu.fullstack.marketplace.model.LoginRequest;
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


    // keyStr is hardcoded here for testing purpose
    // in a real scenario, it should either be stored in a database or injected from the environment
    public static final String keyStr = "secretkey";

    //injected from the environment
    //public static final String keyStr = System.getenv("JWT_SECRET_KEY");

    // t key
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofSeconds(150); //really short validity for testing purpose


    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(final @RequestBody LoginRequest loginRequest) throws Exception {

        //hash input password and compare with stored password
        String hashedPassword = userService.hashPassword(loginRequest.password());

        // if username and password are valid, issue an access token
        // note that subsequent requests need this token
        User user = userService.getUserByUsername(loginRequest.username());

        System.out.println("Checking password: " + loginRequest.password() + " hashed: " + hashedPassword + " stored: " + user.getPassword());
        if (user != null) {
            if (user.getPassword().equals(hashedPassword) || user.getPassword().equals(loginRequest.password()) ) { //last condition is for testing purpose
                return generateToken(user.getId());
            }
        }

        System.out.println("Access denied, wrong credentials....");

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }

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


    @GetMapping(value = "/verify")
    @ResponseStatus(value = HttpStatus.OK)
    public String verifyToken(final @RequestHeader("Authorization") String token) throws Exception {
        // verify access token and return user id
        if (token != null && token.startsWith("Bearer ")) { // Bearer is the default prefix for JWT
            final String jwtToken = token.substring(7);
            final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
            final JWTVerifier verifier = JWT.require(hmac512).build();
            verifier.verify(jwtToken);
            return JWT.decode(jwtToken).getSubject();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }

    public static String getTokenSubject(final String token) {
        try {
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
        catch (Exception e) {
            return null;
        }
    }

}