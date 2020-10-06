package auctionappproject.demo.services;

import auctionappproject.demo.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void addComment(Comment comment);

    List<Comment> getAllCommentsFrom(String username);

    List<Comment> getAllCommentsTo(String username);

    Optional<Comment> findCommentById(String id);

    void deleteComment(String id);
}
