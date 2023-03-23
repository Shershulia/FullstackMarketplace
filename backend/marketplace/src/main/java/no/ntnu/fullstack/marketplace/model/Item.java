package no.ntnu.fullstack.marketplace.model;

import jakarta.persistence.*;

import java.util.List;

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

    @Column (name = "userid", nullable = false)
    private Long userid;

    @Column (name = "price")
    private Long price;

    @Column (name = "location", nullable = false)
    private String location;

    @Column (name = "image", nullable = false)
    private String image;
    @Column (name = "categories")
    private List<String> categories;

    @Column (name = "metadata")
    private List<String> metadata;

    public Item() {
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

    public Long getUserId() {
        return userid;
    }

    public void setUser(Long userid) {
        this.userid = userid;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<String> metadata) {
        this.metadata = metadata;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
