package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.android.myownecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText email , password;
    TextView signup;
    Button signinbutton;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.EditTV_email_signin);
        password =  findViewById(R.id.EditTV_pass_sigin);
        signup =  findViewById(R.id.siguplogin);
        signinbutton = findViewById(R.id.Button_signin);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                startActivity(intent);
                loginuser();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

    }

    private void loginuser() {

        String useremail = email.getText().toString();
        String userpass = password.getText().toString();

        if (TextUtils.isEmpty(userpass)){
            Toast.makeText(LoginActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(useremail)){
            Toast.makeText(LoginActivity.this, "Please enter the email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userpass.length()<6){
            Toast.makeText(LoginActivity.this, "Please enter the correct format password", Toast.LENGTH_SHORT).show();
            return;
        }

          firebaseAuth.signInWithEmailAndPassword(useremail,userpass)
                  .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()){
                              Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                          }
                          else{
                              //// TODO ;;;;; yha pe dekhna h ki get exception aahega ya nhi.....

                              Toast.makeText(LoginActivity.this, "Error :"+task.getException(), Toast.LENGTH_SHORT).show();
                          }
                      }
                  });

    }
}