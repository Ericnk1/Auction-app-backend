package auctionappproject.demo.services;

import auctionappproject.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * To create a new user
     * @param user
     */

    void addUser(User user);

    /**
     *
     * @return a list of all users
     */

    List<User> findAllUsers();

    /**
     * To find user by username
     * @param username User's username
     * @return user
     */
    User findUserByUsername(String username);

    /**
     * To find user by userName and Password
     * @param username User's name
     * @param password User's password
     * @return Optional of user
     */
    Optional<User> findByUsernameAndPassword(String username, String password);

    void updateUser(Long username, User user);

    Optional<User> findUserById(Long id);

    void deleteUser(Long id);

    Optional<User> findUserName(String firstName, String lastName);

}
