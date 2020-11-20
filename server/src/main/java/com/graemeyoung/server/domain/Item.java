package com.graemeyoung.server.domain;

public class Item {

    private Integer itemId;
    private Integer quantity;
    private String imageUri;
    private Double price;
    private String name;
    private String description;
    private String category;

    public Item(Integer itemId, Integer quantity, String imageUri, Double price, String name, String description, String category) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.imageUri = imageUri;
        this.price = price;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Integer getitemId() {
        return itemId;
    }

    public void setitemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setimageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
