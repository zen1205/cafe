package com.example.cnpmnc_12;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager_2);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // gắn adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        // disable vuốt ngang nếu muốn chỉ chọn qua bottom nav
        // viewPager2.setUserInputEnabled(false);

        // khi chọn bottom nav -> đổi trang viewpager
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    viewPager2.setCurrentItem(0, false);
                    return true;
                case R.id.menu_history:
                    viewPager2.setCurrentItem(1, false);
                    return true;
                case R.id.menu_account:
                    viewPager2.setCurrentItem(2, false);
                    return true;
            }
            return false;
        });

        // khi lướt viewpager -> đổi icon bottom nav
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_history);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_account);
                        break;
                }
            }
        });
    }
}
