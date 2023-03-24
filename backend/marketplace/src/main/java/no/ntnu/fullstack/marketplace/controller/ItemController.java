package no.ntnu.fullstack.marketplace.controller;

import no.ntnu.fullstack.marketplace.model.Item;
import no.ntnu.fullstack.marketplace.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/item")
    private List<Item> getAllItems() {
        System.out.println("Get all items");
        return itemService.getAllItem();
    }


    @GetMapping("/item/{id}")
    private Item getItem(@PathVariable("id") Long id) {
        Item item = itemService.getItemById(id);
        return item;
    }

    @GetMapping("/user/{id}/items")
    private List<Item> getItemsOfSpecialUser(@PathVariable("id") Long id) {
        return itemService.getAllItemForSpecialUser(id);
    }

    @DeleteMapping("/item/{id}")
    private void deleteItem(@PathVariable("id") Long id)
    {
        itemService.delete(id);
    }

    //creating post mapping that post the student detail in the database
    @PostMapping("/item/update")
    private Long saveItem(@RequestBody Item item, @RequestHeader (name="Authorization") String token)
    {

        String tokenSubject = TokenController.getTokenSubject(token);

        if (!tokenSubject.equals(item.getId().toString())) {
            return 0L;
        }

        System.out.println("Update item");
        System.out.println(item);
        itemService.saveOrUpdate(item);
        return item.getId();

    }

    //create new item and return id
    @PostMapping("/item/new")
    private Long newItem(@RequestBody Item item,@RequestHeader (name="Authorization") String token)
    {
        String tokenSubject = TokenController.getTokenSubject(token);

        if (!tokenSubject.equals(item.getId().toString())) {
            return 0L;
        }
        System.out.println("New item");
        System.out.println(item);
        itemService.newItem(item);
        return item.getId();
    }


}
