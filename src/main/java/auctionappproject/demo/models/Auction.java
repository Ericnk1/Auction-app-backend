package auctionappproject.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String category;
    private String description;
    private double price;
    private String endDate;
    private String auctionDate;
    private String status = "NOT PAID";

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    public Auction(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        this.endDate = item.getEndDate();
        this.seller=item.getSeller();
        this.buyer = item.getBuyer();
        this.category = item.getCategory();

    }

    public Auction(long id, String name, String description, double price, String endDate, String sellerName, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.endDate = endDate;
        this.seller=new User(sellerName,"", "", "", "","", "",0);
        this.buyer = new User("","","","", "","", "",0);
        this.category = category;
    }

    public boolean hasBuyer(){
        if(this.buyer!=null)
            return true;
        else return false;
    }
}
