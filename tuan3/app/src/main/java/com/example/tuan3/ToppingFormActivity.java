package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ToppingFormActivity extends AppCompatActivity {

    private EditText etName, etPrice;
    private Switch swAvailable;
    private Button btnSave;
    private TextView tvTitle;
    private int currentToppingId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topping_form);

        // Ánh xạ Views
        tvTitle = findViewById(R.id.tvFormTitle);
        etName = findViewById(R.id.etToppingName);
        etPrice = findViewById(R.id.etToppingPrice);
        swAvailable = findViewById(R.id.swAvailable);
        btnSave = findViewById(R.id.btnSaveTopping);

        // Kiểm tra Intent để xác định chế độ (Thêm mới hay Sửa)
        if (getIntent().hasExtra("TOPPING_ID")) {
            currentToppingId = getIntent().getIntExtra("TOPPING_ID", -1);
            tvTitle.setText("CHỈNH SỬA TOPPING");
            btnSave.setText("CẬP NHẬT TOPPING");
            loadToppingDataForEdit();
        } else {
            tvTitle.setText("THÊM TOPPING MỚI");
            btnSave.setText("THÊM MỚI TOPPING");
        }

        btnSave.setOnClickListener(v -> saveTopping());
    }

    private void loadToppingDataForEdit() {
        // Lấy dữ liệu từ Intent và điền vào Form
        String name = getIntent().getStringExtra("TOPPING_NAME");
        int price = getIntent().getIntExtra("TOPPING_PRICE", 0);
        boolean isAvailable = getIntent().getBooleanExtra("TOPPING_AVAILABLE", true);

        etName.setText(name);
        etPrice.setText(String.valueOf(price));
        swAvailable.setChecked(isAvailable);
    }

    private void saveTopping() {
        String name = etName.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đủ Tên và Giá.", Toast.LENGTH_SHORT).show();
            return;
        }

        int price;
        try {
            price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá phải là số nguyên.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isAvailable = swAvailable.isChecked();

        if (currentToppingId != -1) {
            // TRƯỜNG HỢP SỬA (UPDATE)
            callUpdateApi(currentToppingId, name, price, isAvailable);
        } else {
            // TRƯỜNG HỢP THÊM MỚI (CREATE)
            callCreateApi(name, price, isAvailable);
        }
    }

    // Hàm giả lập API Create (POST)
    private void callCreateApi(String name, int price, boolean isAvailable) {
        // GỌI RETROFIT API POST TẠI ĐÂY
        Toast.makeText(this, "Thêm mới thành công: " + name, Toast.LENGTH_LONG).show();
        finish();
    }

    // Hàm giả lập API Update (PUT/PATCH)
    private void callUpdateApi(int id, String name, int price, boolean isAvailable) {
        // GỌI RETROFIT API PUT/PATCH TẠI ĐÂY (kèm theo ID)
        Toast.makeText(this, "Cập nhật thành công cho ID: " + id, Toast.LENGTH_LONG).show();
        finish();
    }
}