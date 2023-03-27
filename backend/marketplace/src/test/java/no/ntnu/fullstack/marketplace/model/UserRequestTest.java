package no.ntnu.fullstack.marketplace.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testJsonSerialization() throws Exception {
        UserRequest userRequest = new UserRequest("john123", "john@example.com",
                "password123", "John", "Doe", 25);

        String json = objectMapper.writeValueAsString(userRequest);

        assertEquals("{\"username\":\"john123\",\"email\":\"john@example.com\"," +
                "\"password\":\"password123\",\"name\":\"John\",\"lastname\":\"Doe\"," +
                "\"age\":25}", json);
    }

    @Test
    void testJsonDeserialization() throws Exception {
        String json = "{\"username\":\"jane456\",\"email\":\"jane@example.com\"," +
                "\"password\":\"password456\",\"name\":\"Jane\",\"lastname\":\"Doe\"," +
                "\"age\":30}";

        UserRequest userRequest = objectMapper.readValue(json, UserRequest.class);

        assertEquals("jane456", userRequest.username());
        assertEquals("jane@example.com", userRequest.email());
        assertEquals("password456", userRequest.password());
        assertEquals("Jane", userRequest.name());
        assertEquals("Doe", userRequest.lastname());
        assertEquals(30, userRequest.age());
    }
}
