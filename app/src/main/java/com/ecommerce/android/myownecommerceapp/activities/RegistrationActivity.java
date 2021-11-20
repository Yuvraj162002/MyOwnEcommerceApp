package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.android.myownecommerceapp.Model.UserModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    Button signupbutton;
    TextView signin;
    EditText name,email,pass;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        getSupportActionBar().hide();

        signupbutton = findViewById(R.id.Button_signup);
        signin = findViewById(R.id.siginlogin);
        name = findViewById(R.id.EditTV_name);
        email = findViewById(R.id.EditTV_email_sigup);
        pass = findViewById(R.id.EditTV_pass_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        sharedPreferences = getSharedPreferences("Onboarding",MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean("firsttime",true);

        if (isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firsttime",false);
            editor.commit();

            Intent intent = new Intent(RegistrationActivity.this,OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }

        /// Not login baar baar.....
        if (firebaseAuth.getCurrentUser() != null) {

            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            Toast.makeText(this, "Please wait you are already login in", Toast.LENGTH_SHORT).show();
            finish();
        }




        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegistrationActivity.this , MainActivity.class);
                startActivity(intent);
                createUser();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });





    }

    private void createUser() {

        String entername = name.getText().toString();
        String enteremail  =  email.getText().toString();
        String enterpass = pass.getText().toString();


        if (TextUtils.isEmpty(entername)){
            Toast.makeText(RegistrationActivity.this, "Please enter the name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(enteremail)){
            Toast.makeText(RegistrationActivity.this, "Please enter the email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(enterpass)){
            Toast.makeText(RegistrationActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (enterpass.length()<6){
            Toast.makeText(RegistrationActivity.this, "Please enter the correct format password", Toast.LENGTH_SHORT).show();
            return;
        }
        /// loginUser.......
        firebaseAuth.createUserWithEmailAndPassword(enteremail,enterpass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            UserModel userModel = new UserModel(enteremail, enterpass, entername);
                            String id = task.getResult().getUser().getUid();
                            firebaseDatabase.getReference().child("User").child(id).setValue(userModel);

                            Toast.makeText(RegistrationActivity.this, "You are Succefully Registered", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }

    }


