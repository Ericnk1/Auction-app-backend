package auctionappproject.demo.controllers;

import auctionappproject.demo.models.Product;
import auctionappproject.demo.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping("/bid/{username}/bids")
    public List<Product> getAllUserBids(@PathVariable("username") String username){
        return bidService.getAllBidsByUsername(username);
    }

    @GetMapping("/{id}")
    Optional<Product> getBid(@PathVariable("id") Long id){
        return bidService.getBid(id);
    }

    @PostMapping("/users/{username}/bids/{amount}")
    public void addBid(@RequestBody Product product, @PathVariable String username, @PathVariable double amount){

        bidService.addBid(product, username, amount);
    }

    @PutMapping("/users/{username}/bids/{id}/{amount}")
    public void updateBid(@RequestBody Product product, @PathVariable String username, @PathVariable String id, @PathVariable double amount){

        bidService.updateBid(product, amount);
    }
}
