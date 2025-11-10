package com.example.tuan4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFormActivity extends AppCompatActivity {

    private EditText etName, etPrice, etDescription, etImageUrl;
    private Switch swAvailable;
    private Button btnSave;
    private TextView tvTitle;
    private ImageView ivProductImagePreview;
    private int currentProductId = -1;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        apiService = RetrofitClient.getApiService();

        // Ánh xạ Views
        tvTitle = findViewById(R.id.tvProductFormTitle);
        etName = findViewById(R.id.etProductName);
        etPrice = findViewById(R.id.etProductPrice);
        etDescription = findViewById(R.id.etProductDescription);
        etImageUrl = findViewById(R.id.etProductImageUrl);
        swAvailable = findViewById(R.id.swProductAvailable);
        btnSave = findViewById(R.id.btnSaveProduct);
        ivProductImagePreview = findViewById(R.id.ivProductImagePreview);

        // Khởi tạo ở chế độ Thêm mới
        tvTitle.setText("THÊM ĐỒ UỐNG MỚI");
        btnSave.setText("THÊM MỚI ĐỒ UỐNG");

        btnSave.setOnClickListener(v -> saveProduct());
    }

    // Hàm gọi khi nhấn nút Lưu (Chỉ ở chế độ Thêm mới)
    private void saveProduct() {
        String name = etName.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String imageUrl = etImageUrl.getText().toString().trim();

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

        // Tạo đối tượng Model để gửi đi
        AdminProductModel newProduct = new AdminProductModel(0, name, price, description, imageUrl, isAvailable);

        callCreateApi(newProduct);
    }

    // Logic gọi API Create (POST)
    private void callCreateApi(AdminProductModel product) {
        apiService.createProduct(product).enqueue(new Callback<AdminProductModel>() {
            @Override
            public void onResponse(Call<AdminProductModel> call, Response<AdminProductModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductFormActivity.this, "Thêm mới đồ uống thành công!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ProductFormActivity.this, "Thêm mới thất bại: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AdminProductModel> call, Throwable t) {
                Toast.makeText(ProductFormActivity.this, "Lỗi kết nối API: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}