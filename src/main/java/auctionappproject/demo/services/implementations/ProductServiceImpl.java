package auctionappproject.demo.services.implementations;

import auctionappproject.demo.models.Product;
import auctionappproject.demo.repositories.ProductRepository;
import auctionappproject.demo.repositories.UserRepository;
import auctionappproject.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductByCategory(String category) {
        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.findByCategory(category));
        return products;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }


    @Override
    public List<Product> getSortedProducts() {
        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.findAll());
        products.sort(Comparator.comparingDouble(Product::getPrice)
        );
        return products;
    }

    @Override
    public List<Product> getAllUserProducts(String username) {

        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.findBySellerUsername(username));
        return products;
    }

    @Override
    public List<Product> findByEndDate(String date) {
        return productRepository.findByEndDate(date);
    }

    @Override
    public void addProduct(Product product, String username) {

        product.setSeller(userRepository.findByUsername(username));
        productRepository.save(product);

    }

    @Override
    public void updateProduct(Product product) {

        product.setBuyer(productRepository.findById(product.getId()).get().getBuyer());
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long id) {

        findProductById(id).ifPresent(product -> {
            product.setActive(false);
            updateProduct(product);
        });

    }
}
