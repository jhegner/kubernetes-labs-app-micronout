package org.jhegner.k8slabs;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Product {

    private String createdAt;
    private String name;
    private String avatar;
    private String id;
    private String description;
    private String price;

    public Product(String createdAt, String name, String avatar, String id, String description, String price) {
        this.createdAt = createdAt;
        this.name = name;
        this.avatar = avatar;
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
