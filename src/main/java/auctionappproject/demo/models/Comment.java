package auctionappproject.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public User fromWhom =new  User();

    @ManyToOne
    public User toWhom = new User();

    public Comment(String description, User fromWhom, User toWhom) {

        Date curTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.dateTime = sdf.format(curTime);
        this.description = description;
        this.fromWhom = fromWhom;
        this.toWhom = toWhom;
        this.id = 1L;
    }
}
