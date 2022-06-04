package com.example.elections;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FirebaseAuth fAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private TextView emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseDatabase=FirebaseDatabase.getInstance();
       // databaseReference=firebaseDatabase.getReference("Email");
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Email");
        emailid=findViewById(R.id.emailid);
        String Email=emailid.getText().toString();
        getdata();

        bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent5=new Intent(profile.this,home.class);
                        startActivity(intent5);

                        return  true;
                    case R.id.recent:
                         Intent intent3=new Intent(profile.this,recents.class);
                        startActivity(intent3);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false
                        ;
            }
        });
    }

    private void getdata() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 String Email=snapshot.getValue(String.class);
                Log.d(TAG,""+Email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG,"Failed to read data"+error);
            }
        });
    }

    public void signout(View view) {
        fAuth.getInstance().signOut();
        Intent intent=new Intent(profile.this,loginpage.class);
       startActivity(intent);
    }

    public TextView getEmailid() {
        return emailid;
    }

    public void setEmailid(TextView emailid) {
        this.emailid = emailid;
    }

}