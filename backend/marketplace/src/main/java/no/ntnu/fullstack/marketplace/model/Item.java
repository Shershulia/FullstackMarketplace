package no.ntnu.fullstack.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ITEMDATA")
public class Item {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "description", nullable = false)
    private String description;

    @Column (name = "username", nullable = false)
    private Long username;

    @Column (name = "price", nullable = false)
    private Long price;

    @Column (name = "location", nullable = false)
    private String location;

    @Column (name = "metadata", nullable = false)
    private String metadata;

    public Item() {
    }
    public Item(String name, String description, Long username, Long price, String location, String metadata) {
        this.name = name;
        this.description = description;
        this.username = username;
        this.price = price;
        this.location = location;
        this.metadata = metadata;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
