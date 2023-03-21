package no.ntnu.fullstack.marketplace.service;

import no.ntnu.fullstack.marketplace.model.User;
import no.ntnu.fullstack.marketplace.repository.UserRepository;
import no.ntnu.fullstack.marketplace.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user){
        Optional<User> userDb = this.userRepository.findById(user.getId());

        if (userDb.isPresent()) {
                User userUpdate = userDb.get();
                userUpdate.setId(user.getId());
                userUpdate.setName(user.getName());
                userUpdate.setUserName(user.getUsername());
//                userUpdate.setEmail(user.getEmail());
//                userUpdate.setPhone(user.getPhone());
//                userUpdate.setPassword(user.getPassword());

                userRepository.save(userUpdate);
                return userUpdate;
            } else {
                throw new ResourceNotFoundException("Record not found with id : " + user.getId());
            }
    }
    @Override
    public List < User > getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {

        Optional < User > userDb = this.userRepository.findById(id);

        if (userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }
    @Override
    public User getUserByUsername(String username){
            //get all users and check if username exists in the list of users and return the user if it exists
            List<User> users = this.userRepository.findAll();
            for(User user : users){
                if(user.getUsername().equals(username)){
                    return user;
                }
            }
            throw new ResourceNotFoundException("Record not found with username : " + username);
    }

    @Override
    public void deleteUser(long userId) {
        Optional < User > userDb = this.userRepository.findById(userId);

        if (userDb.isPresent()) {
            this.userRepository.delete(userDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + userId);
        }

    }
}