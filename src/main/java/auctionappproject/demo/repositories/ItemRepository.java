package auctionappproject.demo.repositories;

import auctionappproject.demo.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findBySellerUsername (String username);
    List<Item> findByBuyerUsername (String username);
    List<Item> findByCategory (String category);
    List<Item> findByEndDate (String category);
    Optional<Item>  findById (Long Id);
    Optional<Item> findByName(String name);
}
