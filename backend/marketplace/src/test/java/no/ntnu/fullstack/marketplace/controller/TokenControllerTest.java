package no.ntnu.fullstack.marketplace.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import no.ntnu.fullstack.marketplace.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TokenControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    TokenController tokenController;

    private static final String keyStr = "secretkey";

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateToken() throws Exception {
        final String actualToken = tokenController.generateToken(1L);

        // Verify that the token is of the correct format and that it is signed with the correct key
        JWT.require(Algorithm.HMAC512(keyStr))
                .withIssuer("fullstack_marketplace_api")
                .build()
                .verify(actualToken);

        // Verify that the token contains the correct user id
        final String actualUserId = JWT.decode(actualToken).getSubject();
        assertEquals("1", actualUserId);


    }


}
