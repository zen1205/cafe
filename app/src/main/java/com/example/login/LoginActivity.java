package com.example.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.login.R;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private RadioButton rdbAdmin, rdbUser;
    private boolean isEnableButtonLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        rdbAdmin = findViewById(R.id.rdb_admin);
        rdbUser = findViewById(R.id.rdb_user);

        rdbUser.setChecked(true);
        btnLogin.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();
                boolean ok = !email.isEmpty() && !pass.isEmpty();
                btnLogin.setEnabled(ok);
                isEnableButtonLogin = ok;
            }
        };

        edtEmail.addTextChangedListener(watcher);
        edtPassword.addTextChangedListener(watcher);

        btnLogin.setOnClickListener(v -> onClickValidateLogin());
    }

    private void onClickValidateLogin() {
        if (!isEnableButtonLogin) return;

        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty()) {
            showToast("Vui lòng nhập email");
        } else if (password.isEmpty()) {
            showToast("Vui lòng nhập mật khẩu");
        } else {
            showToast("Đăng nhập thành công (giả lập)");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
