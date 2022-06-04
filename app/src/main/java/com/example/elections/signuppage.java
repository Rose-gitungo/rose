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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class signuppage extends AppCompatActivity {

    private Button signup;
    private TextInputLayout Firstname;
    private TextInputLayout Lastname;
    private TextInputLayout Email;
    private TextInputLayout phoneno;
    private TextInputLayout idnumber;
    private TextInputLayout Password;
    private TextInputLayout Confirmpassword;

    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;

    FirebaseDatabase firebaseDatabase;
    CustomerInfo customerinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        Firstname = findViewById(R.id.first);
        Lastname = findViewById(R.id.last);
        Email = findViewById(R.id.emailsign);
        phoneno = findViewById(R.id.phone);
        idnumber = findViewById(R.id.idno);
        Password = findViewById(R.id.password2);
        Confirmpassword = findViewById(R.id.confirmpassword);
        signup = findViewById(R.id.signup);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("CustomerInfo");

        customerinfo = new CustomerInfo();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = Objects.requireNonNull(Firstname.getEditText()).getText().toString();
                String lastname = Lastname.getEditText().getText().toString();
                String email = Email.getEditText().getText().toString();
                String password = Password.getEditText().getText().toString();
                String confirmpassword = Confirmpassword.getEditText().getText().toString();
                String Phoneno = phoneno.getEditText().getText().toString();
                String idno = idnumber.getEditText().getText().toString();


                if (TextUtils.isEmpty(firstname)) {
                    Toast.makeText(signuppage.this, "Input First Name", Toast.LENGTH_SHORT).show();
                } else {
                    if (TextUtils.isEmpty(lastname)) {
                        Toast.makeText(signuppage.this, "Input Last Name", Toast.LENGTH_SHORT).show();
                    } else {
                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(signuppage.this, "Input Email", Toast.LENGTH_SHORT).show();
                        } else {
                            if (TextUtils.isEmpty(Phoneno)) {
                                Toast.makeText(signuppage.this, "Input phoneno", Toast.LENGTH_SHORT).show();
                            } else {
                                if (TextUtils.isEmpty(idno)) {
                                    Toast.makeText(signuppage.this, "Input ID number", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (TextUtils.isEmpty(password)) {
                                        Toast.makeText(signuppage.this, "Input password", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (TextUtils.isEmpty(confirmpassword)) {
                                            Toast.makeText(signuppage.this, "This field cannot be empty", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (!confirmpassword.equals(password)) {
                                                Toast.makeText(signuppage.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                                            } else {
                                                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(signuppage.this, "User created ", Toast.LENGTH_SHORT).show();
                                                            FirebaseUser user = firebaseAuth.getCurrentUser();
                                                            addDatatoFirebase();
                                                            Intent intent4 = new Intent(signuppage.this, home.class);
                                                            startActivity(intent4);
                                                            updateUI(user);
                                                        } else {
                                                            Toast.makeText(signuppage.this, "user account creation failed", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

               /* private void addDatatoFirebase () {


                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.setValue("CustomerInfo");
                            Toast.makeText(signuppage.this, "Data added", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            databaseReference.setValue("CustomerInfo");
                            Toast.makeText(signuppage.this, "Data not added" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }*/
            }
        });


    }

    private void addDatatoFirebase() {
        String firstname = Objects.requireNonNull(Firstname.getEditText()).getText().toString();
        String lastname = Lastname.getEditText().getText().toString();
        String email = Email.getEditText().getText().toString();
        String password = Password.getEditText().getText().toString();
        String confirmpassword = Confirmpassword.getEditText().getText().toString();
        String Phoneno = phoneno.getEditText().getText().toString();
        String idno = idnumber.getEditText().getText().toString();

        customerinfo.setfirst(firstname);
        customerinfo.setlast(lastname);
        customerinfo.setEmail(email);
        customerinfo.setPassword2(password);
        customerinfo.setPhoneno(Phoneno);
        customerinfo.setIdno(idno);
        customerinfo.setConfrimpassword(confirmpassword);
    }

    private void updateUI(FirebaseUser user) {
        FirebaseUser currentuser =firebaseAuth.getCurrentUser();
        if (currentuser!=null){
            Toast.makeText(signuppage.this, "you signed in successully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(signuppage.this, "You did not Sign in", Toast.LENGTH_SHORT).show();
        }
    }

    public void tologinpage(View view) {
        Intent intent=new Intent(signuppage.this,loginpage.class);
        startActivity(intent);
    }
}