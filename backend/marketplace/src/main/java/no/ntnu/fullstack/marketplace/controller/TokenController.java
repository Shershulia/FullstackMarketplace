package no.ntnu.fullstack.marketplace.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import no.ntnu.fullstack.marketplace.dao.MockDao;
import no.ntnu.fullstack.marketplace.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
public class TokenController {


    // keyStr is hardcoded here for testing purpose
    // in a real scenario, it should either be stored in a database or injected from the environment
    public static final String keyStr = "secretkey";

    //injected from the environment
    //public static final String keyStr = System.getenv("JWT_SECRET_KEY");

    // t key
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofSeconds(15); //really short validity for testing purpose


    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(final @RequestBody LoginRequest loginRequest) throws Exception {
        // if username and password are valid, issue an access token
        // note that subsequent requests need this token
        if (MockDao.checkUserCredentials(loginRequest.username(), loginRequest.password())) {
            return generateToken(loginRequest.username());
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }

    public String generateToken(final String userId) {
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
        final JWTVerifier verifier = JWT.require(hmac512).build();
        return JWT.create()
                .withSubject(userId)
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
}