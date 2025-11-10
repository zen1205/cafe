package com.example.tuan3;

// Interface dùng để giao tiếp từ Adapter về Activity
public interface OnToppingActionListener {
    void onEditClick(AdminToppingModel topping);
    void onDeleteClick(AdminToppingModel topping);
}