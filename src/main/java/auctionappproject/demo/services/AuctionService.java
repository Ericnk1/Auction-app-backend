package auctionappproject.demo.services;

import auctionappproject.demo.models.Auction;
import auctionappproject.demo.models.Product;

import java.util.List;

public interface AuctionService {

    List<Auction> getBuyerAuctions(String username);

    List<Auction> getSellerAuctions(String username);

    void auctionPayment(String username, Long auctionId);

    void addAuction(Auction auction);
    void deleteAuction(Long id);

}
