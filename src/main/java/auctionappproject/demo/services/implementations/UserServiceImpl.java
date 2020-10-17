package auctionappproject.demo.services.implementations;

import auctionappproject.demo.models.User;
import auctionappproject.demo.repositories.UserRepository;
import auctionappproject.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        user.setActive(true);
        userRepository.save(user);

    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void updateUser(Long id, User user) {

        userRepository.saveAndFlush(user);

    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {

        findUserById(id).ifPresent(user -> {
            userRepository.delete(user);
            /*user.setActive(false);
            updateUser(user.getUsername(), user);*/
        });

    }

    @Override
    public Optional<User> findUserName(String firstName, String lastname) {
        return userRepository.findByFirstNameAndLastName(firstName, lastname);
    }


}
