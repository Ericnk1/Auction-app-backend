package auctionappproject.demo.controllers;

import auctionappproject.demo.models.Item;
import auctionappproject.demo.models.User;
import auctionappproject.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/user") //Mapping to be updated.
    public List<Item> getAllUserItems() {
        User user = new User();
        return itemService.getAllUserItems(user.getUsername());
    }

    @GetMapping("/items/id")
    public Optional<Item> getItemById(@PathVariable Long id){
        return itemService.findItemById(id);
    }

    @GetMapping("/items/{category}")
    public List<Item> getAllItemsByCategory(@PathVariable String category){
        return itemService.getAllItemsByCategory(category);
    }

    @GetMapping("/items/{name}")
    public Optional<Item> getItemByName(@PathVariable String name){
        return itemService.findItemByName(name);
    }

    @PostMapping
    public ResponseEntity addItem(@RequestBody Item item, User user) {
        itemService.addItem(item, user.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Mapping to be updated.
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateItem(@RequestBody Item item, @PathVariable String username) {
        item.setSeller(new User(username,"","","", "", "", "",0));
        itemService.updateItem(item);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(new Date().toInstant());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity  deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/item/date")
    public List<Item> getItemByEndDate(@PathVariable String date){
        return itemService.findByEndDate(date);}



    public List<Item> getAllSortedItems() {
        return itemService.getSortedItems();
    }
}
