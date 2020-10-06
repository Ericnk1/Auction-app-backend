package auctionappproject.demo.services.implementations;

import auctionappproject.demo.models.Comment;
import auctionappproject.demo.repositories.CommentRepository;
import auctionappproject.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllCommentsFrom(String username){
        System.out.println(1);
        List<Comment> comments = new ArrayList<>();
        comments.addAll(commentRepository.findByFromWhomUsername(username));
        return comments;

    }

    public List<Comment> getAllCommentsTo(String username){

        System.out.println(0);
        List<Comment> comments = new ArrayList<>();
        comments.addAll(commentRepository.findByToWhomUsername(username));

        return comments;

    }

    @Override
    public Optional<Comment> findCommentById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteComment(String id) {
        findCommentById(id).ifPresent(comment -> {
            commentRepository.delete(comment);
        });

    }


    @Override
    public void addComment(Comment comment) {

        commentRepository.save(comment);

    }
}
