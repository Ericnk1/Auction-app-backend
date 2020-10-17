package auctionappproject.demo;

import auctionappproject.demo.models.Auction;
import auctionappproject.demo.models.Product;
import auctionappproject.demo.repositories.AuctionRepository;
import auctionappproject.demo.repositories.ProductRepository;
import auctionappproject.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class Scheduling {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteEndLots() {
        Date curTime = new Date();
        DateFormat dtfrm = DateFormat.getDateInstance();
        String endDate = dtfrm.format(curTime);

        List<Product> products = new ArrayList<>();
        productRepository.findByEndDate(endDate)
                .forEach(products::add);
        products.stream()
                .peek((e)
                                -> {
                            Auction auction = new Auction(e);
                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            String nowDate = sdf.format(cal.getTime());
                            try {
                                Date d = sdf.parse(nowDate);
                                long msdate = d.getTime();
                                long twoDays = 2*86400000L;

                                Date ms = new Date(msdate+twoDays);
                                auction.setAuctionDate(sdf.format(ms));

                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            auctionRepository.save(auction);

                        }
                )
                .forEach(productRepository:: delete);

    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkAuctions(){
        Date currentTime = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance();
        String endDate = dateFormat.format(currentTime);
        List<Auction> auctions = new ArrayList<>();
        auctions.addAll(auctionRepository.findByAuctionDate(endDate));
        for(Auction auction: auctions){
            if(auction.getStatus().equals("NOT PAID")){
                auction.setStatus("CLOSED");
                auction.getBuyer().addPenalty(0.3*auction.getPrice());//Add penalty
                userRepository.save(auction.getBuyer());
                auctionRepository.save(auction);
            }
        }
    }
}
