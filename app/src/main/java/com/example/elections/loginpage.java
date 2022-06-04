package com.example.elections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpage extends AppCompatActivity {
Button  loginbutton;
 private TextInputLayout email;
    private TextInputLayout password;
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginbutton=findViewById(R.id.loginbutton);

        fAuth=FirebaseAuth.getInstance();

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email= email.getEditText().getText().toString();
                String Password= password.getEditText().getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(loginpage.this,"Input Email address",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(loginpage.this, "Input Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(loginpage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user=fAuth.getCurrentUser();
                            UpdateUI(user);
                        }else {
                            Toast.makeText(loginpage.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                            UpdateUI(null);
                        }
                    }
                });

            }
        });

    }
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentuser=fAuth.getCurrentUser();
        if(currentuser!=null){
        UpdateUI(currentuser);
        }
    }

    private void UpdateUI(FirebaseUser currentuser) {
        if(currentuser!=null){
            Toast.makeText(this,"You Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,home.class));
        }else{
            Toast.makeText(this,"You Did not signed in",Toast.LENGTH_LONG).show();
        }
        }

    public void tosignup(View view) {
        Intent intent1= new Intent(loginpage.this,signuppage.class);
        startActivity(intent1);
    }

    public void toforgotpassword(View view) {
        Intent intent2=new Intent(loginpage.this,forgotpassword.class);
        startActivity(intent2);
    }
}