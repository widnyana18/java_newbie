package com.example.latihanfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this, "Anda Memilih Home", Toast.LENGTH_SHORT).show();
                        fragmentHome();
                        break;

                    case R.id.menu_favorit:
                        Toast.makeText(MainActivity.this, "Anda Memilih Favorite", Toast.LENGTH_SHORT).show();
                        fragmentFavorit();
                        break;

                    case R.id.menu_about:
                        Toast.makeText(MainActivity.this, "Anda Memilih About", Toast.LENGTH_SHORT).show();
                        fragmentAbout();
                        break;

                    case R.id.menu_mahasiswa:
                        getFragmentPage(new MahasiswaFragment());
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

    public void fragmentHome() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        HomeFragment home = new HomeFragment();
        transaction.replace(R.id.main_container, home);
        transaction.commit();
    }

    public void fragmentFavorit() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        Favorite_Fragment favorit = new Favorite_Fragment();
        transaction.replace(R.id.main_container, favorit);
        transaction.commit();
    }

    public void fragmentAbout() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        AboutFragment about = new AboutFragment();
        transaction.replace(R.id.main_container, about);
        transaction.commit();
    }
}


