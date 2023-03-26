package no.ntnu.fullstack.marketplace.service;

import no.ntnu.fullstack.marketplace.model.Item;
import no.ntnu.fullstack.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Service class for Item model for handling database operations and representation of data to the controller layer
 */
@Service
public class ItemService
{
    /**
     * Autowired repository for Item model for handling database operations
     *
     */
    @Autowired
    ItemRepository itemRepository;
    //getting all item records
    /**
     * All basic categories for item that user can set during item creation
     *
     */
    private List<String> basicCategories = new ArrayList<>(Arrays.asList("Electronics","Clothing and Accessories",
            "Home and Garden", "Health and Beauty", "Sports and Outdoors"));

    /**
     * Get all items from the database and return them as a list
     * @return List of all items in the database
     */
    public List<Item> getAllItem()
    {
        List<Item> items = new ArrayList<Item>();
        itemRepository.findAll().forEach(item -> items.add(item));
        return items;
    }

    /**
     * Get all categories from the database and return them as a list
     * @return List of all categories in the database
     */
    public List<String> getAllCategory()
    {
        HashSet<String> categories = new HashSet<String>();
        //for each item in the database, add each element in the categories list in the item to the categories list
        itemRepository.findAll().forEach(item -> categories.addAll(item.getCategories()));
        //convert the hashset to a list
        List<String> categoriesList = new ArrayList<String>(categories);
        return categoriesList;
    }

    /**
     * Get all available categories from the database for item creation
     * @return List of all categories in the database
     */
    public List<String> getAvailableCategory()
    {
        return basicCategories;
    }
    /**
     * Add category
     * @param  category category added to basic categories
     */
    public void addCategory(String category)
    {
        basicCategories.add(category);
    }
    /**
     * Modify category
     * @param  categoryModifyed category should be modifyed from basic categories
     * @param  categoryModifyed category should be modifyed to basic categories
     */
    public void modify(String categoryModifyed,String newCategory)
    {
        itemRepository.findAll().forEach(item -> {
            if (item.getCategories().contains(categoryModifyed)){
                List<String> nowCategories = item.getCategories();
                nowCategories.remove(categoryModifyed);
                nowCategories.add(newCategory);
                item.setCategories(nowCategories);
                itemRepository.save(item);
            }
        });
        basicCategories.remove(categoryModifyed);
        basicCategories.add(newCategory);
    }

    /**
     * Get all item from the database
     * @param id The id of the item to get
     * @return item with the given id from the database
     */
    //getting a specific record
    public Item getItemById(Long id)
    {
        return itemRepository.findById(id).get();
    }

    /**
     * create or update an item in the database
     * @param item The item to save or update
     */
    public void saveOrUpdate(Item item)
    {
        itemRepository.save(item);
    }


    /**
     * create a new item in the database
     * @param item The item to save
     */
    public void newItem(Item item)
    {
        itemRepository.save(item);
    }

    /**
     * delete an item from the database
     * @param id The id of the item to delete
     */
    //deleting a specific record
    public void delete(Long id)
    {
        itemRepository.deleteById(id);
    }
    public List<Item> getAllItemForSpecialUser(Long id) {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(item -> {
            if (item.getUserId().equals(id)) {
                items.add(item);
            }
        });
        return items;
    }
}