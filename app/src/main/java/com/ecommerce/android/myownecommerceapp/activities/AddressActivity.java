package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ecommerce.android.myownecommerceapp.Model.AddressModel;
import com.ecommerce.android.myownecommerceapp.Model.CartModel;
import com.ecommerce.android.myownecommerceapp.Model.New_Product_model;
import com.ecommerce.android.myownecommerceapp.Model.PopularProductModel;
import com.ecommerce.android.myownecommerceapp.Model.ViewAllModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.adapter.AddressAdpater;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdpater.SelectedAddress {

    Button addtoaddress , payment;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
  private List<AddressModel> addressModelList;
  private AddressAdpater addressAdpater;
    String mAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addtoaddress = findViewById(R.id.add_to_address);
        payment = findViewById(R.id.payment);
        recyclerView = findViewById(R.id.recy_address);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        /// get Data from detailed activity.....
        Object obj = getIntent().getSerializableExtra("item");

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList = new ArrayList<>();
        addressAdpater  = new AddressAdpater(addressModelList, getApplicationContext(),this);
        recyclerView.setAdapter(addressAdpater);

        firebaseFirestore.collection("CurrentUser").document(firebaseAuth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult().getDocuments()) {

                        AddressModel addressModel = document.toObject(AddressModel.class);
                        addressModelList.add(addressModel);
                        addressAdpater.notifyDataSetChanged();

                    }
            }
                    }
        });


        addtoaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this ,AddAdress.class ));
            }
        });
     payment.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             double amount = 0.0;

             if (obj instanceof New_Product_model){
                 New_Product_model new_product_model = (New_Product_model) obj;
                 amount = new_product_model.getPrice();
             }
             if (obj instanceof ViewAllModel){
                 ViewAllModel viewAllModel = ( ViewAllModel) obj;
                 amount = viewAllModel.getPrice();
             }
             if (obj instanceof PopularProductModel){
                 PopularProductModel popularProductModel = (PopularProductModel) obj;
                 amount = popularProductModel.getPrice();
             }

             Intent intent = new Intent(AddressActivity.this , PaymentActivity.class);
             intent.putExtra("amount",amount);
             startActivity(intent);
         }
     });


    }

    @Override
    public void setAddress(String address) {

        mAddress = address;
    }
}