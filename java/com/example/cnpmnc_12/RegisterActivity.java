package com.example.cnpmnc_12;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private RadioGroup radioGroup;
    private RadioButton rdbAdmin, rdbUser;
    private Button btnRegister;
    private LinearLayout layoutLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // ánh xạ view
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        radioGroup = findViewById(R.id.rdb_admin).getParent() instanceof RadioGroup ?
                (RadioGroup) findViewById(R.id.rdb_admin).getParent() : null;
        rdbAdmin = findViewById(R.id.rdb_admin);
        rdbUser = findViewById(R.id.rdb_user);
        btnRegister = findViewById(R.id.btn_register);
        layoutLogin = findViewById(R.id.layout_login);

        // xử lý đăng ký
        btnRegister.setOnClickListener(v -> {
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
            if (radioGroup != null && radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, getString(R.string.message_enter_infor), Toast.LENGTH_SHORT).show();
                return;
            }

            // ⚡ demo đăng ký thành công → về LoginActivity
            Toast.makeText(this, getString(R.string.register), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // quay lại LoginActivity khi bấm "Đã có tài khoản?"
        layoutLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
