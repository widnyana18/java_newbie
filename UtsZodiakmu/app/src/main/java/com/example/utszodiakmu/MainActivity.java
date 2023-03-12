package com.example.utszodiakmu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentPage(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new
                                                                         BottomNavigationView.OnNavigationItemSelectedListener() {
                                                                             @Override
                                                                             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             switch (item.getItemId()) {
                 case R.id.menu_home:
                     Toast.makeText(MainActivity.this, "Anda Memilih Home", Toast.LENGTH_SHORT).show();
                     getFragmentPage(new HomeFragment());
                     break;

                 case R.id.menu_about:
                     Toast.makeText(MainActivity.this, "Anda Memilih About", Toast.LENGTH_SHORT).show();
                     getFragmentPage(new AboutFragment());
                     break;
             }
             return true;
            }
            });
    }

    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}


