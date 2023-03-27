package no.ntnu.fullstack.marketplace.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemTest {
    private Item item;

    @BeforeEach
    public void setUp() {
        item = new Item();
    }

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String name = "Item1";
        String description = "This is an item";
        Long userId = 2L;
        Long price = 100L;
        String location = "Trondheim";
        List<String> image = Arrays.asList("image1.jpg", "image2.jpg");
        List<String> categories = Arrays.asList("category1", "category2");
        List<String> metadata = Arrays.asList("metadata1", "metadata2");

        item.setId(id);
        item.setName(name);
        item.setDescription(description);
        item.setUserid(userId);
        item.setPrice(price);
        item.setLocation(location);
        item.setImage(image);
        item.setCategories(categories);
        item.setMetadata(metadata);

        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(description, item.getDescription());
        assertEquals(userId, item.getUserid());
        assertEquals(price, item.getPrice());
        assertEquals(location, item.getLocation());
        assertEquals(image, item.getImage());
        assertEquals(categories, item.getCategories());
        assertEquals(metadata, item.getMetadata());
    }

    @Test
    public void testItemConstructor() {
        String name = "Item1";
        String description = "This is an item";
        Long userId = 2L;
        Long price = 100L;
        String location = "Trondheim";
        List<String> image = Arrays.asList("image1.jpg", "image2.jpg");
        List<String> categories = Arrays.asList("category1", "category2");
        List<String> metadata = Arrays.asList("metadata1", "metadata2");

        item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setUserid(userId);
        item.setPrice(price);
        item.setLocation(location);
        item.setImage(image);
        item.setCategories(categories);
        item.setMetadata(metadata);

        assertEquals(name, item.getName());
        assertEquals(description, item.getDescription());
        assertEquals(userId, item.getUserid());
        assertEquals(price, item.getPrice());
        assertEquals(location, item.getLocation());
        assertEquals(image, item.getImage());
        assertEquals(categories, item.getCategories());
        assertEquals(metadata, item.getMetadata());
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(item);
    }
}
