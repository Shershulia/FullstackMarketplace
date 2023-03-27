package no.ntnu.fullstack.marketplace.service;

import no.ntnu.fullstack.marketplace.model.Item;
import no.ntnu.fullstack.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService
{
    @Autowired
    ItemRepository itemRepository;
    //getting all item records
    public List<Item> getAllItem()
    {
        List<Item> items = new ArrayList<Item>();
        itemRepository.findAll().forEach(item -> items.add(item));
        return items;
    }
    //getting a specific record
    public Item getItemById(Long id)
    {
        return itemRepository.findById(id).get();
    }


    public void saveOrUpdate(Item item)
    {
        itemRepository.save(item);
    }

    public void newItem(Item item)
    {
        itemRepository.save(item);
    }

    //deleting a specific record
    public void delete(Long id)
    {
        itemRepository.deleteById(id);
    }

}