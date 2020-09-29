package auctionappproject.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String topic;
    private String description;
    private double price;
    private String endDate;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    public boolean hasBuyer(){
        if(this.buyer!=null)
            return true;
        else return false;
    }

}
