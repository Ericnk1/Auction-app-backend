package auctionappproject.demo.services;

import auctionappproject.demo.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllItems();

    List<Item> getAllItemsByCategory(String category);

    Optional<Item> findItemById(Long id);

    Optional<Item> findItemByName(String name);

    List<Item> getSortedItems();

    List<Item> getAllUserItems(String username);

    List<Item> findByEndDate (String date);

    void addItem(Item item, String username);

    void updateItem(Item item);

    void deleteItem(Long id);
}
