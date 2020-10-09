package auctionappproject.demo.services.implementations;

import auctionappproject.demo.exceptions.InvalidLoginException;
import auctionappproject.demo.models.Login;
import auctionappproject.demo.models.User;
import auctionappproject.demo.services.LoginService;
import auctionappproject.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public void validateLogin(Login login) throws InvalidLoginException {

        Optional<User> userOptional = userService.findByUsernameAndPassword(login.getUsername(), login.getPassword());

        if (!userOptional.isPresent()){
            throw new InvalidLoginException(login.getUsername());
        }
    }
}
