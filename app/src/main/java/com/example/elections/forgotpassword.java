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
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    Button button3;
    private TextInputLayout emailreset;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        emailreset=findViewById(R.id.emailreset);
        fAuth=FirebaseAuth.getInstance();

        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email= emailreset.getEditText().getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(forgotpassword.this,"Input Email address",Toast.LENGTH_SHORT).show();
                    return;
                }

                fAuth.sendPasswordResetEmail(String.valueOf(emailreset)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(forgotpassword.this, "Email sent.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(forgotpassword.this,loginpage.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(forgotpassword.this,"Account not found",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}