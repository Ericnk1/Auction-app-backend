package auctionappproject.demo.services;

import auctionappproject.demo.models.Item;

import java.util.List;
import java.util.Optional;

public interface BidService {

    List<Item> getAllBidsByUsername(String username);

    void addBid(Item item, String username, double amount);

    Optional<Item> getBid(String id);

    void updateBid(Item item,  double amount);
}
