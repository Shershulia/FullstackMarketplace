package no.ntnu.fullstack.marketplace.controller;

import no.ntnu.fullstack.marketplace.model.Item;
import no.ntnu.fullstack.marketplace.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ItemController itemController = new ItemController();
        itemController.itemService = itemService;
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    void testGetAllItems() throws Exception {
        Item item1 = new Item();
        item1.setId(1L);
        item1.setName("item1");
        item1.setDescription("description1");
        item1.setPrice(100L);
        item1.setUserid(2L);
        Item item2 = new Item();
        item2.setId(2L);
        item2.setName("item2");
        item2.setDescription("description2");
        item2.setPrice(200L);
        item2.setUserid(3L);

        List<Item> items = Arrays.asList(item1, item2);
        when(itemService.getAllItem()).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders.get("/item")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("item1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(100.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userid").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("item2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("description2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(200.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userid").value(3L));
    }

    @Test
    void testGetItem() throws Exception {
        Item item = new Item();
        item.setId(1L);
        item.setName("item1");
        item.setDescription("description1");
        item.setPrice(100L);
        item.setUserid(2L);
        when(itemService.getItemById(1L)).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.get("/item/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("item1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userid").value(2L));
    }

    // test for deleteItem method

    // test for buyItem method

    // test for saveItem method

    // test for newItem method

}
