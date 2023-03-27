package no.ntnu.fullstack.marketplace.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserInfoResponseTest {

    @Test
    public void testUserInfoResponse() {
        UserInfoResponse userInfoResponse = new UserInfoResponse("John", "Doe", "Male", 30, "johndoe", "password");

        assertEquals("John", userInfoResponse.getFirstName());
        assertEquals("Doe", userInfoResponse.getLastName());
        assertEquals("Male", userInfoResponse.getGender());
        assertEquals(30, userInfoResponse.getAge());
        assertEquals("johndoe", userInfoResponse.getUsername());
        assertEquals("password", userInfoResponse.getPassword());
    }

    @Test
    public void testGetters() {
        UserInfoResponse userInfoResponse = new UserInfoResponse("John", "Doe", "Male", 30, "johndoe", "password");

        assertEquals("John", userInfoResponse.getFirstName());
        assertEquals("Doe", userInfoResponse.getLastName());
        assertEquals("Male", userInfoResponse.getGender());
        assertEquals(30, userInfoResponse.getAge());
        assertEquals("johndoe", userInfoResponse.getUsername());
        assertEquals("password", userInfoResponse.getPassword());
    }

}
