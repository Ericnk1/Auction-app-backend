package auctionappproject.demo.repositories;

import auctionappproject.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySellerUsername (String username);
    List<Product> findByBuyerUsername (String username);
    List<Product> findByCategory (String category);
    List<Product> findByEndDate (String category);
    Optional<Product> findById (Long Id);
    List<Product> findByName(String name);
}
