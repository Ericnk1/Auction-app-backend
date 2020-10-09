package auctionappproject.demo.services.implementations;

import auctionappproject.demo.models.Item;
import auctionappproject.demo.models.User;
import auctionappproject.demo.repositories.ItemRepository;
import auctionappproject.demo.repositories.UserRepository;
import auctionappproject.demo.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getAllBidsByUsername(String username) {
        List<Item> items = new ArrayList<>();
        items.addAll(itemRepository.findByBuyerUsername(username));
        return items;
    }

    @Override
    public void addBid(Item item, String username, double amount) {

        Optional<Item> tempItem = itemRepository.findById(item.getId());
        User user = userRepository.findByUsername(username);
        if(user.getPenalty()==0 && tempItem.get().getPrice()< amount ) {
            tempItem.get().setPrice(amount);
            item.setSeller(itemRepository.findById(item.getId()).get().getSeller());
            item.setBuyer(new User(username,"", "", "", "", "", "", 0));
            itemRepository.save(item);
        }

    }

    @Override
    public Optional<Item> getBid(String id) {
        return itemRepository.findById(id);
    }

    @Override
    public void updateBid(Item item, double amount) {

        Optional<Item> tempItem = itemRepository.findById(item.getId());
        if(tempItem.get().getPrice()< amount ) {
            tempItem.get().setPrice(amount);
            item.setSeller(tempItem.get().getSeller());
            item.setBuyer(tempItem.get().getBuyer());
            itemRepository.save(item);
        }
    }


}
