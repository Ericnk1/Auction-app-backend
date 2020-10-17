package auctionappproject.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String description;
    private double price;
    private String endDate;
    private boolean isActive;

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
