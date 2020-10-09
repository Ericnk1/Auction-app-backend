package auctionappproject.demo.services;

import auctionappproject.demo.exceptions.InvalidLoginException;
import auctionappproject.demo.models.Login;

public interface LoginService {

    /**
     * To check whether the login is valid or not
     *
     * @param login login
     */
    void validateLogin(Login login) throws InvalidLoginException;
}
