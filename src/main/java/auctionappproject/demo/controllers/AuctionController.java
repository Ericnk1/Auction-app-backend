package auctionappproject.demo.controllers;

import auctionappproject.demo.models.Auction;
import auctionappproject.demo.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping
    public ResponseEntity addAuction(Auction auction){
        auctionService.addAuction(auction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{username}/auctions")
    public List<Auction> getBuyerAuctions(@PathVariable String username) {
        return auctionService.getBuyerAuctions(username);
    }

    @GetMapping("/users/{username}/sales")
    public List<Auction> getSellerAuctions(@PathVariable String username){
            return auctionService.getSellerAuctions(username);
        }

    @PutMapping
    public ResponseEntity auctionPayment(@PathVariable String username,@PathVariable Long auctionId ) {
        auctionService.auctionPayment(username, auctionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAuction(Long id){
        auctionService.deleteAuction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
