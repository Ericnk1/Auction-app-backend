package auctionappproject.demo.controllers;

import auctionappproject.demo.models.User;
import auctionappproject.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
