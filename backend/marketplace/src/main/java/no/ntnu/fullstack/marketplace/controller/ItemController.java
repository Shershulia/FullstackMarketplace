package no.ntnu.fullstack.marketplace.controller;

import no.ntnu.fullstack.marketplace.model.Item;
import no.ntnu.fullstack.marketplace.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Item objects in the database
 * used to handle requests from the frontend and return responses
 * Handles GET, POST, PUT and DELETE requests for items in the database
 */
@RestController
@CrossOrigin
public class ItemController {
    /**
     * ItemService object used to handle requests to the database for items
     */
    @Autowired
    ItemService itemService;

    /**
     * GET request to get all items in the database
     * @return List of all items in the database
     */
    @GetMapping("/item")
    private List<Item> getAllItems() {
        System.out.println("Get all items");
        return itemService.getAllItem();
    }

    /**
     * GET request to get all categories in the database
     * @return List of all categories in the database (no duplicates)
     */
    @GetMapping("/item/categories")
    private List<String> getAllCategories() {
        System.out.println("Get all items");
        return itemService.getAllCategory();
    }

    /**
     * GET request to get available for creation categories in the database
     * @return List of all categories in the database (no duplicates)
     */
    @GetMapping("/item/creation-categories")
    private List<String> getAvailableCategories() {
        return itemService.getAvailableCategory();
    }

    /**
     * GET request to get available for creation categories in the database
     * @param token JWT token containing the id of the user, needed in header to verify that the user is allowed to add category
     */
    @PostMapping("/item/creation-categories")
    private void addCategory(@RequestBody String category,@RequestHeader (name="Authorization") String token) {
        //  if (!TokenController.verifyToken(token)) {
        //      throw new RuntimeException("Invalid token");
        // }
         itemService.addCategory(category);
        System.out.println("Category added");

    }




    /**
     * GET request to get a specific item in the database
     * @param id id of the item to get
     * @return Item object with the specified id
     */

    @GetMapping("/item/{id}")
    private Item getItem(@PathVariable("id") Long id) {
        Item item = itemService.getItemById(id);
        return item;
    }

    @GetMapping("/user/{id}/items")
    private List<Item> getItemsOfSpecialUser(@PathVariable("id") Long id) {
        return itemService.getAllItemForSpecialUser(id);
    }

    /**
     * DELETE request to delete a specific item in the database
     * @param id id of the item to delete
     */
    @DeleteMapping("/item/{id}")
    private void deleteItem(@PathVariable("id") Long id)
    {
        itemService.delete(id);
    }

    /**
     * POST request to update a specific item in the database
     * @param item Item object with the updated information
     * @param token JWT token containing the id of the user, needed in header to verify that the user is allowed to update the item
     * @return id of the updated item
     */
    //creating post mapping that post the student detail in the database
    @PostMapping("/item/update")
    private Long saveItem(@RequestBody Item item, @RequestHeader (name="Authorization") String token)
    {

        String tokenSubject = TokenController.getTokenSubject(token);

        if (!tokenSubject.equals(item.getUserId().toString())) {
            return 0L;
        }

        System.out.println("Update item");
        System.out.println(item);
        itemService.saveOrUpdate(item);
        return item.getId();

    }

    /**
     * POST request to create a new item in the database
     * @param item Item object with the information of the new item
     * @return id of the new item
     */
    //create new item and return id
    @PostMapping("/item/new")
    private Long newItem(@RequestBody Item item)
    {
        System.out.println("New item");
        System.out.println(item);
        itemService.newItem(item);
        return item.getId();
    }


}
