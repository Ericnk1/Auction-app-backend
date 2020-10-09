package auctionappproject.demo.services;

import auctionappproject.demo.models.Auction;

import java.util.List;

public interface AuctionService {

    List<Auction> getBuyerAuctions(String username);

    List<Auction> getSellerAuctions(String username);

    void auctionPayment(String username, String auctionId);

}
