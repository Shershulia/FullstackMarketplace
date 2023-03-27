package no.ntnu.fullstack.marketplace.service;

import no.ntnu.fullstack.marketplace.model.Item;
import no.ntnu.fullstack.marketplace.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllItem() {
        List<Item> itemList = new ArrayList<>();
        Item item = new Item();
        item.setName("Test Item");
        item.setPrice(1000L); // Price is now a long
        itemList.add(item);

        when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> result = itemService.getAllItem();

        assertThat(result, hasSize(1)); // Ensure the result list has only one element
        assertThat(result.get(0).getName(), equalTo("Test Item")); // Ensure the name is correct
        assertThat(result.get(0).getPrice(), equalTo(1000L)); // Ensure the price is correct
    }

    @Test
    void testGetItemById() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Test Item");
        item.setPrice(1000L);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Item result = itemService.getItemById(1L);

        assertThat(result.getName(), equalTo("Test Item")); // Ensure the name is correct
        assertThat(result.getPrice(), equalTo(1000L)); // Ensure the price is correct
    }
    @Test
    void testSaveOrUpdate() {
        Item item = new Item();
        item.setName("Test Item");
        item.setPrice(1000L);

        itemService.saveOrUpdate(item);

        // Verify that the item was saved
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void testNewItem() {
        Item item = new Item();
        item.setName("Test Item");
        item.setPrice(1000L);

        itemService.newItem(item);

        // Verify that the item was saved
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void testDelete() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Test Item");
        item.setPrice(1000L);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        itemService.delete(1L);

        // Verify that the item was deleted
        verify(itemRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllItemEmpty() {
        List<Item> itemList = new ArrayList<>();

        when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> result = itemService.getAllItem();

        assertThat(result, hasSize(0)); // Ensure the result list is empty
    }

}
