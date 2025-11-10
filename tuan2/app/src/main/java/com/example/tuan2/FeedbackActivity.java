package com.example.tuan2; // <--- THAY THẾ BẰNG TÊN PACKAGE THỰC TẾ CỦA BẠN

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler; // Cần import Handler cho phần giả lập
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

public class FeedbackActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText editTextFeedback;
    private Spinner spinnerTopic;
    private TextView textViewCharCount;
    private Button buttonSubmit;
    private static final int MAX_CHAR_COUNT = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Lỗi 'Cannot resolve symbol R' thường sẽ tự động sửa khi có package
        setContentView(R.layout.activity_feedback);

        // Ánh xạ View
        ratingBar = findViewById(R.id.ratingBar);
        editTextFeedback = findViewById(R.id.editTextFeedback);
        spinnerTopic = findViewById(R.id.spinnerTopic);
        textViewCharCount = findViewById(R.id.textViewCharCount);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        setupListeners();
    }

    // --- 2. Phương thức Thiết lập Lắng nghe Sự kiện ---
    private void setupListeners() {
        // Lắng nghe sự kiện nhập liệu của EditText (theo dõi ký tự)
        editTextFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int currentLength = s.length();
                // Cập nhật số ký tự đã nhập
                textViewCharCount.setText(currentLength + "/" + MAX_CHAR_COUNT);
                // Gọi hàm kiểm tra tính hợp lệ
                checkInputValidity();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Lắng nghe sự kiện thay đổi RatingBar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Gọi hàm kiểm tra tính hợp lệ mỗi khi rating thay đổi
                checkInputValidity();
            }
        });

        // Lắng nghe nhấn nút Gửi
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    // --- 3. Phương thức Kiểm tra Tính hợp lệ (Bật/Tắt nút Gửi) ---
    private void checkInputValidity() {
        float rating = ratingBar.getRating();
        String feedbackContent = editTextFeedback.getText().toString().trim();

        // Nút được bật nếu: Rating > 0 HOẶC Nội dung góp ý không trống
        if (rating > 0 || !feedbackContent.isEmpty()) {
            buttonSubmit.setEnabled(true);
        } else {
            buttonSubmit.setEnabled(false);
        }
    }

    // --- 4. Phương thức Xử lý Gửi Dữ liệu ---
    private void submitFeedback() {
        // 1. Thu thập dữ liệu
        float rating = ratingBar.getRating();
        String content = editTextFeedback.getText().toString().trim();
        String topic = spinnerTopic.getSelectedItem().toString(); // Lấy chủ đề được chọn

        // 2. Xác thực chủ đề (Nếu chủ đề đầu tiên là "Chọn chủ đề")
        if (topic.equals("Chọn chủ đề")) {
            Toast.makeText(this, "Vui lòng chọn một chủ đề góp ý.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. Vô hiệu hóa nút
        buttonSubmit.setEnabled(false);

        // 4. GỌI API BACKEND Ở ĐÂY
        // Thay thế bằng Retrofit/Volley/AsyncTask

        // Hiện tại, dùng Handler để giả lập quá trình gửi thành công
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Xử lý sau khi gửi thành công:

                // Hiển thị thông báo thành công
                Toast.makeText(FeedbackActivity.this, "Cảm ơn bạn! Phản hồi của bạn đã được gửi đến CoffeeX.", Toast.LENGTH_LONG).show();

                // Quay lại màn hình trước
                finish();
            }
        }, 2000); // Giả lập độ trễ 2 giây
    }
}