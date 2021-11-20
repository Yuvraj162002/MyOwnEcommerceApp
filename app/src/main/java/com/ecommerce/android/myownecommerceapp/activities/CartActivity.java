package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.android.myownecommerceapp.Model.CartModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.adapter.CartAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {



    TextView overallAmount;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    List<CartModel> cartModelList;
    CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recy_my_cart);
        overallAmount = findViewById(R.id.TV_total_price_cart);



        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        cartModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartModelList);
        recyclerView.setAdapter(cartAdapter);
        firebaseFirestore.collection("CurrentUser").document(firebaseAuth.getCurrentUser().getUid())
                .collection("AddToCart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {

                                String documentId = document.getId();
                                CartModel mycartModel = document.toObject(CartModel.class);
                                cartModelList.add(mycartModel);
                                cartAdapter.notifyDataSetChanged();

                                mycartModel.setDocumentId(documentId);


                            }
                            calculateTotalAmount(cartModelList);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    ///// To get Totalprice  of the cart quantity.....

    private void calculateTotalAmount(List<CartModel> cartModelList) {
        double totalAmount = 0.0;
        for (CartModel cartModel : cartModelList){
            totalAmount += cartModel.getTotalPrice();
        }

        overallAmount.setText("Total Amount :"+totalAmount+"$");

    }
}