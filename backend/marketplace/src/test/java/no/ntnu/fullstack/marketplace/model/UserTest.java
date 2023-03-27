package no.ntnu.fullstack.marketplace.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testuser", "testemail@test.com", "testpassword", "Test", "User", 25 ,"normal");
    }

    @Test
    void getId() {
        assertNull(user.getId());
    }

    @Test
    void setId() {
        Long newId = 1L;
        user.setId(newId);
        assertEquals(newId, user.getId());
    }

    @Test
    void getUsername() {
        assertEquals("testuser", user.getUsername());
    }

    @Test
    void setUsername() {
        String newUsername = "newuser";
        user.setUsername(newUsername);
        assertEquals(newUsername, user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    void setPassword() {
        String newPassword = "newpassword";
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("testemail@test.com", user.getEmail());
    }

    @Test
    void setEmail() {
        String newEmail = "newemail@test.com";
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void getName() {
        assertEquals("Test", user.getName());
    }

    @Test
    void setName() {
        String newName = "New";
        user.setName(newName);
        assertEquals(newName, user.getName());
    }

    @Test
    void getLastname() {
        assertEquals("User", user.getLastname());
    }

    @Test
    void setLastname() {
        String newLastname = "Surname";
        user.setLastname(newLastname);
        assertEquals(newLastname, user.getLastname());
    }

    @Test
    void getAge() {
        assertEquals(25, user.getAge());
    }

    @Test
    void setAge() {
        Integer newAge = 30;
        user.setAge(newAge);
        assertEquals(newAge, user.getAge());
    }

    // @Test
    // void testToString() {
    //     String expectedToString = "User{id=null, username='testuser', password='testpassword', email='testemail@test.com', name='Test', lastname='User', age=25}";
    //     assertEquals(expectedToString, user.toString());
    // }
}
