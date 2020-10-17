package auctionappproject.demo.controllers;

import auctionappproject.demo.models.User;
import auctionappproject.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id){
        userService.findUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{firstName,lastName}")
    public ResponseEntity getUserByName(@PathVariable("firstName,lastName") String firstName, String lastname){
        userService.findUserName(firstName, lastname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable("id") Long id){
        userService.updateUser(id,user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(new Date().toInstant());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity  deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<User> getAllUsers() {return userService.findAllUsers();}
}
