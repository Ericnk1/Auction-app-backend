package auctionappproject.demo.services;

import auctionappproject.demo.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getAllProductByCategory(String category);

    Optional<Product> findProductById(Long id);

    List<Product> findProductByName(String name);

    List<Product> getSortedProducts();

    List<Product> getAllUserProducts(String username);

    List<Product> findByEndDate (String date);

    void addProduct(Product product, String username);

    void updateProduct(Product product);

    void deleteProduct(Long id);
}
