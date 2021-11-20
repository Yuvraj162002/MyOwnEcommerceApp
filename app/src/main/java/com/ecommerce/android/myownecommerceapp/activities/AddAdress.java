package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ecommerce.android.myownecommerceapp.Model.AddAddressModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAdress extends AppCompatActivity {

    TextInputLayout  name , city ,address, postalcode ,phn;
    Button addaddressbtn;


    FirebaseDatabase firebaseDatabase;
   DatabaseReference databaseReference;
   FirebaseFirestore firebaseFirestore;
   FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        name = findViewById(R.id.name_address);
        city = findViewById(R.id.TV_city);
        address = findViewById(R.id.TV_address_lane);
        postalcode = findViewById(R.id.TV_postalcode);
        phn = findViewById(R.id.TV_phoneNo);
        addaddressbtn = findViewById(R.id.btn_add_address);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        addaddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Users");
                //Get all the values
                String username = name.getEditText().getText().toString();
                String usercity = city.getEditText().getText().toString();
                String useraddress = address.getEditText().getText().toString();
                String userphn = phn.getEditText().getText().toString();
                String userpostal = postalcode.getEditText().getText().toString();

                String final_address = "";

                if (!username.isEmpty()){
                    final_address+=username+", ";
                }
                if (!usercity.isEmpty()){
                    final_address+=usercity+", ";
                }
                if (!useraddress.isEmpty()){
                    final_address+=useraddress+", ";
                }
                if (!userpostal.isEmpty()){
                    final_address+=userpostal+", ";
                }
                if (!userphn.isEmpty()){
                    final_address+=userphn+", ";
                }

                if (!username.isEmpty() && !useraddress.isEmpty() && !usercity.isEmpty() && !userpostal.isEmpty() && !userphn.isEmpty()){
                    Map<String, String> map = new HashMap<>();
                    map.put("userAddress",final_address);

                    firebaseFirestore.collection("CurrentUser").document(firebaseAuth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                               if (task.isSuccessful()){
                                   Toast.makeText(AddAdress.this, "Address Added Successfully!!", Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(AddAdress.this,ProductDetailedActivity.class));
                                   finish();
                               }
                               else {
                                   Toast.makeText(AddAdress.this, "Please Fill all the Information", Toast.LENGTH_SHORT).show();
                               }
                        }
                    });
                }
            }
        });
    }
}