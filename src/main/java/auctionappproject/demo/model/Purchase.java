package auctionappproject.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table
public class Purchase {

    @Id
    private long id;
    private String name;
    private String topic;
    private String description;
    private double price;
    private String endDate;
    private String purchaseDate;
    private String status = "NOT PAID";

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;
}
