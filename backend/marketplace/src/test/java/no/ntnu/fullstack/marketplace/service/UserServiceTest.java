package no.ntnu.fullstack.marketplace.service;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        //given
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);

        //when
        List<User> result = userService.getAllUser();

        //then
        assertEquals(userList, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        //given
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //when
        User result = userService.getUserById(1L);

        //then
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetUserByUsername() {
        //given
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        when(userRepository.findAll()).thenReturn(List.of(user));

        //when
        User result = userService.getUserByUsername("testuser");

        //then
        assertEquals(user, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testSaveOrUpdate() {
        //given
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        //when
        userService.saveOrUpdate(user);

        //then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testNewUser() {
        //given
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        //when
        userService.newUser(user);

        //then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDelete() {
        //given
        Long userId = 1L;

        //when
        userService.delete(userId);

        //then
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testHashPassword() {
        //given
        String password = "testPassword";

        //when
        String hashedPassword = userService.hashPassword(password);

        //then
        assertEquals(64, hashedPassword.length());
        //assert that the password is not the same as the hashed password
        assertNotEquals(password, hashedPassword);
        //assert that the hashed password is the same every time
        assertEquals(hashedPassword, userService.hashPassword(password));
    }
}
