package com.example.tuan3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class AdminToppingActivity extends AppCompatActivity implements OnToppingActionListener {

    private RecyclerView recyclerView;
    private AdminToppingAdapter adapter;
    private List<AdminToppingModel> toppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_topping);

        recyclerView = findViewById(R.id.recyclerViewAdminToppings);
        Button btnAdd = findViewById(R.id.btnAddNewTopping);

        toppingList = new ArrayList<>();
        adapter = new AdminToppingAdapter(this, toppingList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadToppingsList();

        // Xử lý sự kiện click nút Thêm mới
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(AdminToppingActivity.this, ToppingFormActivity.class);
            startActivity(intent);
        });
    }

    // Phương thức giả lập tải dữ liệu (sẽ thay bằng API GET)
    private void loadToppingsList() {
        toppingList.clear();
        toppingList.add(new AdminToppingModel(1, "Trân châu Đen", 5000, true));
        toppingList.add(new AdminToppingModel(2, "Kem Phô Mai", 10000, true));
        toppingList.add(new AdminToppingModel(3, "Thạch Cà Phê", 7000, false));
        adapter.notifyDataSetChanged();
    }

    // Xử lý sự kiện SỬA (Mở Form)
    @Override
    public void onEditClick(AdminToppingModel topping) {
        Intent intent = new Intent(this, ToppingFormActivity.class);
        intent.putExtra("TOPPING_ID", topping.getId());
        intent.putExtra("TOPPING_NAME", topping.getName());
        intent.putExtra("TOPPING_PRICE", topping.getPrice());
        intent.putExtra("TOPPING_AVAILABLE", topping.isAvailable());
        startActivity(intent);
    }

    // Xử lý sự kiện XÓA
    @Override
    public void onDeleteClick(AdminToppingModel topping) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa topping: " + topping.getName() + "?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    callDeleteToppingApi(topping.getId());
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void callDeleteToppingApi(int toppingId) {
        // GỌI RETROFIT API DELETE TẠI ĐÂY
        Toast.makeText(this, "Đang gửi yêu cầu xóa ID: " + toppingId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load lại danh sách khi quay về (sau khi thêm/sửa)
        loadToppingsList();
    }
}