package auctionappproject.demo.controllers;

import auctionappproject.demo.models.Product;
import auctionappproject.demo.models.User;
import auctionappproject.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/user") //Mapping to be updated.
    public List<Product> getAllUserProducts() {
        User user = new User();
        return productService.getAllUserProducts(user.getUsername());
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        Optional<Product> optionalItem = productService.findProductById(id);

        if(optionalItem.isPresent()) {
            return new ResponseEntity<>(optionalItem.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-by-category/{category}")
    public List<Product> getAllIProductsByCategory(@PathVariable("category") String category){
        return productService.getAllProductByCategory(category);
    }

    @GetMapping("get-by-name/{name}")
    public List<Product> getProductsByName(@PathVariable("name") String name){
        return productService.findProductByName(name);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product, User user) {
        productService.addProduct(product, user.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Mapping to be updated.
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable String username) {
        product.setSeller(new User(username,"","","", "", "", "",0));
        productService.updateProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(new Date().toInstant());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("get-by-date/{date}")
    public List<Product> getProductByEndDate(@PathVariable("date") String date){
        return productService.findByEndDate(date);}



    public List<Product> getAllSortedProducts() {
        return productService.getSortedProducts();
    }
}
