package auctionappproject.demo.controllers;

import auctionappproject.demo.models.Comment;
import auctionappproject.demo.models.User;
import auctionappproject.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/users/{username}")
    public List<Comment> getAllCommentsFromUser(@PathVariable String username) {

        return commentService.getAllCommentsFrom(username);
    }

    @GetMapping("/users/{username}")
    public List<Comment> getAllCommentsToUser(@PathVariable String username){

        return commentService.getAllCommentsTo(username);
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody String description, @PathVariable String fromWhom, @PathVariable String toWhom){
        Comment comment = new Comment(description,new User(fromWhom,"","","","","", "",0),new User(toWhom,"","","", "","", "",0));
        commentService.addComment(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity deleteComment(String id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
