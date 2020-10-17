package auctionappproject.demo.services.implementations;

import auctionappproject.demo.models.Product;
import auctionappproject.demo.models.User;
import auctionappproject.demo.repositories.ProductRepository;
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
    ProductRepository productRepository;

    @Override
    public List<Product> getAllBidsByUsername(String username) {
        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.findByBuyerUsername(username));
        return products;
    }

    @Override
    public void addBid(Product product, String username, double amount) {

        Optional<Product> tempItem = productRepository.findById(product.getId());
        User user = userRepository.findByUsername(username);
        if(user.getPenalty()==0 && tempItem.get().getPrice()< amount ) {
            tempItem.get().setPrice(amount);
            product.setSeller(productRepository.findById(product.getId()).get().getSeller());
            product.setBuyer(new User(username,"", "", "", "", "", "", 0));
            productRepository.save(product);
        }

    }

    @Override
    public Optional<Product> getBid(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void updateBid(Product product, double amount) {

        Optional<Product> tempItem = productRepository.findById(product.getId());
        if(tempItem.get().getPrice()< amount ) {
            tempItem.get().setPrice(amount);
            product.setSeller(tempItem.get().getSeller());
            product.setBuyer(tempItem.get().getBuyer());
            productRepository.save(product);
        }
    }




}
