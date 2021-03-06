package auctionappproject.demo.services.implementations;

import auctionappproject.demo.models.Auction;
import auctionappproject.demo.models.Product;
import auctionappproject.demo.models.User;
import auctionappproject.demo.repositories.AuctionRepository;
import auctionappproject.demo.repositories.UserRepository;
import auctionappproject.demo.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Auction> getBuyerAuctions(String username) {
        List<Auction> auctions = new ArrayList<>();
        auctions.addAll(auctionRepository.findByBuyerUsername(username));
        return auctions;
    }

    @Override
    public List<Auction> getSellerAuctions(String username) {
        List<Auction> auctions = new ArrayList<>();
        auctions.addAll(auctionRepository.findBySellerUsername(username));
        return auctions;
    }

    @Override
    public void auctionPayment(String username, Long auctionId) {

        Auction tempAuction = auctionRepository.getOne(auctionId);
        if(tempAuction.getStatus().equals("NOT PAID")) {
            User tempBuyerUser = userRepository.getOne(tempAuction.getBuyer().getId());
            if (tempBuyerUser.getBalance() >= tempAuction.getPrice()) {
                tempBuyerUser.subtractMoney(tempAuction.getPrice());
                userRepository.save(tempBuyerUser);
                User tempSellerUser = userRepository.getOne(tempAuction.getSeller().getId());
                tempSellerUser.addMoney(tempAuction.getPrice());
                userRepository.save(tempSellerUser);
            }
            tempAuction.setStatus("PAID");
            auctionRepository.save(tempAuction);
        }
    }

    @Override
    public void addAuction(Auction auction) {
       auctionRepository.save(auction);
    }

    @Override
    public void deleteAuction(Long id) {
        auctionRepository.findById(id).ifPresent(auction ->
                auctionRepository.delete(auction));
    }

}
