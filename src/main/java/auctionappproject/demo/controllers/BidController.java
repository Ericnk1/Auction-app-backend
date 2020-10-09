package auctionappproject.demo.controllers;

import auctionappproject.demo.models.Item;
import auctionappproject.demo.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping("/users/{username}/bids")
    public List<Item> getAllUserBids(@PathVariable String username){
        return bidService.getAllBidsByUsername(username);
    }

    @GetMapping("/bids/{id}")
    Optional<Item> getBid(@PathVariable String id){
        return bidService.getBid(id);
    }

    @PostMapping("/users/{username}/bids/{amount}")
    public void addBid(@RequestBody Item item, @PathVariable String username, @PathVariable double amount){

        bidService.addBid(item, username, amount);
    }

    @PutMapping("/users/{username}/bids/{id}/{amount}")
    public void updateBid(@RequestBody Item item, @PathVariable String username,@PathVariable String id, @PathVariable double amount){

        bidService.updateBid(item, amount);
    }
}
