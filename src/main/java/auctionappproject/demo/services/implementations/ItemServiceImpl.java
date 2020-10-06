package auctionappproject.demo.services.implementations;

import auctionappproject.demo.models.Item;
import auctionappproject.demo.repositories.ItemRepository;
import auctionappproject.demo.repositories.UserRepository;
import auctionappproject.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getAllItemsByCategory(String category) {
        List<Item> items = new ArrayList<>();
        items.addAll(itemRepository.findByCategory(category));
        return items;
    }

    @Override
    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<Item> findItemByName(String name) {
        return itemRepository.findByName(name);
    }


    @Override
    public List<Item> getSortedItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(itemRepository.findAll());
        items.sort(Comparator.comparingDouble(Item::getPrice)
        );
        return items;
    }

    @Override
    public List<Item> getAllUserItems(String username) {

        List<Item> items = new ArrayList<>();
        items.addAll(itemRepository.findBySellerUsername(username));
        return items;
    }

    @Override
    public List<Item> findByEndDate(String date) {
        return itemRepository.findByEndDate(date);
    }

    @Override
    public void addItem(Item item, String username) {

        item.setSeller(userRepository.findByUsername(username));
        itemRepository.save(item);

    }

    @Override
    public void updateItem(Item item) {

        item.setBuyer(itemRepository.findById(item.getId()).get().getBuyer());
        itemRepository.save(item);

    }

    @Override
    public void deleteItem(Long id) {

        findItemById(id).ifPresent(item -> {
            item.setActive(false);
            updateItem(item);
        });

    }
}
