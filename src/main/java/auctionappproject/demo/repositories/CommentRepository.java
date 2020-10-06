package auctionappproject.demo.repositories;

import auctionappproject.demo.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    Comment findById (long username);

    public List<Comment> findByFromWhomUsername (String username);

    public List<Comment> findByToWhomUsername (String username);

    void deleteById(String id);
}
