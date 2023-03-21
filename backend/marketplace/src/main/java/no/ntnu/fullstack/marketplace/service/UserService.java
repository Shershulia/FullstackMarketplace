package no.ntnu.fullstack.marketplace.service;

import no.ntnu.fullstack.marketplace.model.User;
import java.util.List;
public interface UserService {
    User createUser(User user);
    User updateUser(User user);

    List<User> getAllUser();

    User getUserById(long id);

    User getUserByUsername(String username);

    void deleteUser(long id);

}
