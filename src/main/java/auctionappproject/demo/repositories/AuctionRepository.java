package auctionappproject.demo.repositories;

import auctionappproject.demo.models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findBySellerUsername (String username);
    List<Auction> findByBuyerUsername (String username);
    List<Auction> findByAuctionDate(String username);
}
