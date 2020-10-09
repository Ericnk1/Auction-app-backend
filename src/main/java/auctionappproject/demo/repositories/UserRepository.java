package auctionappproject.demo.repositories;

import auctionappproject.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByUsernameAndPassword(String username, String password);

    //Optional<User> findById(Long id);
}
