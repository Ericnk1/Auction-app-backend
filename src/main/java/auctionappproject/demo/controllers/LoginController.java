package auctionappproject.demo.controllers;

import auctionappproject.demo.exceptions.InvalidLoginException;
import auctionappproject.demo.models.Login;
import auctionappproject.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> postLogin(@RequestBody Login login) throws InvalidLoginException {
        try {
            loginService.validateLogin(login);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (InvalidLoginException exception)
        {return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);}
    }
}
