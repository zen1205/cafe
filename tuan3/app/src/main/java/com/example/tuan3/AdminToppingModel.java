package com.example.tuan3;

public class AdminToppingModel {
    private int id;
    private String name;
    private int price;
    private boolean isAvailable;

    public AdminToppingModel(int id, String name, int price, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // GETTERS
    public int getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }

    // SETTERS (Cần thiết cho quá trình cập nhật dữ liệu)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(int price) { this.price = price; }
    public void setAvailable(boolean available) { isAvailable = available; }
}