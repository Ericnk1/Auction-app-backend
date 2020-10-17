package auctionappproject.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String description;
    private double price;
    private String endDate;
    private String auctionDate;
    private String status = "NOT PAID";
    //private byte[] photo;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    public Auction(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.endDate = product.getEndDate();
        this.seller= product.getSeller();
        this.buyer = product.getBuyer();
        this.category = product.getCategory();

    }

    public boolean hasBuyer(){
        if(this.buyer!=null)
            return true;
        else return false;
    }
}
