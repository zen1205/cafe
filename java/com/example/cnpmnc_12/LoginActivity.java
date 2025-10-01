package com.example.cnpmnc_12;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private RadioGroup radioGroup;
    private RadioButton rdbAdmin, rdbUser;
    private Button btnLogin;
    private TextView tvForgotPassword, tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // ánh xạ view
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        radioGroup = findViewById(R.id.rdb_admin).getParent() instanceof RadioGroup ?
                (RadioGroup) findViewById(R.id.rdb_admin).getParent() : null;
        rdbAdmin = findViewById(R.id.rdb_admin);
        rdbUser = findViewById(R.id.rdb_user);
        btnLogin = findViewById(R.id.btn_login);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);

        // TextView "Đăng ký" nằm trong layout_register (LinearLayout cuối file XML)
        TextView tvRegisterLabel = ((TextView) ((android.view.ViewGroup) findViewById(R.id.layout_register)).getChildAt(1));
        tvRegister = tvRegisterLabel;

        // xử lý login
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, getString(R.string.msg_email_require), Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, getString(R.string.msg_password_require), Toast.LENGTH_SHORT).show();
                return;
            }

            // kiểm tra role chọn
            if (radioGroup != null && radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, getString(R.string.message_enter_infor), Toast.LENGTH_SHORT).show();
                return;
            }

            // ⚡ demo login thành công → sang MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // chuyển qua RegisterActivity khi bấm "Đăng ký"
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // xử lý Quên mật khẩu
        tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(this, getString(R.string.forgot_password), Toast.LENGTH_SHORT).show()
        );
    }
}
