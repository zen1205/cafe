package com.example.tuan4;

public class AdminProductModel {
    private int id;
    private String name;
    private int price;
    private String description;
    private String imageUrl;
    private boolean isAvailable;

    public AdminProductModel(int id, String name, int price, String description, String imageUrl, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public boolean isAvailable() { return isAvailable; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setAvailable(boolean available) { isAvailable = available; }
}