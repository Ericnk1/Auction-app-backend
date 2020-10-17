package auctionappproject.demo.services;

import auctionappproject.demo.models.Product;

import java.util.List;
import java.util.Optional;

public interface BidService {

    List<Product> getAllBidsByUsername(String username);

    void addBid(Product product, String username, double amount);

    Optional<Product> getBid(Long id);

    void updateBid(Product product, double amount);
}
