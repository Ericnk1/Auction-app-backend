package auctionappproject.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String description;
    public String dateTime;

    @ManyToOne
    public User seller =new  User();

    @ManyToOne
    public User buyer = new User();
}
