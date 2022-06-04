package com.example.elections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class recents extends AppCompatActivity {
BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recents);
        bottomNavigationView=findViewById(R.id.bottom_navigation);

      bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent2=new Intent(recents.this,home.class);
                        startActivity(intent2);
                        return true;
                    case R.id.recent:
                        return true;

                    case R.id.profile:
                        Intent intent3=new Intent(recents.this,profile.class);
                        startActivity(intent3);
                        return true;
            }
                        return false;
            }
        });

    }
}