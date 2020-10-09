package auctionappproject.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Data
@NoArgsConstructor
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

   // @Size(min=3, max=20,
            //message="Username must be between 3 and 20 characters long.")
    //@Pattern(regexp="^[a-zA-Z0-9]+$",
            //message="Username must be alphanumeric with no spaces")
    private String username;
    //@Size(min=6, max=20,
            //message="The password must be at least 6 characters long.")
    private String password;

    private String firstName;
    private String lastName;
    //@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
           // message="Invalid email address.")
    private String email;
    private String phoneNumber;
    private String address;
    private double balance;
    private double penalty = 0;// 30% from unpaid lot

    private boolean isActive;
    @OneToOne(cascade = CascadeType.MERGE)
    private Authority authority;

    public User(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, double balance) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.balance = balance;
    }

    public void addPenalty(double penalty) {
        this.penalty += penalty;
    }


    public void addMoney(double amount) {
        this.balance +=amount;
    }

    public void subtractMoney(double amount) {
        this.balance -= amount;
    }
}
