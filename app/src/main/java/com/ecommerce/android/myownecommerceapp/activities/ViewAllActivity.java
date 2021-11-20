package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ecommerce.android.myownecommerceapp.Model.CategoryModel;
import com.ecommerce.android.myownecommerceapp.Model.ViewAllModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.adapter.CategoryAdapter;
import com.ecommerce.android.myownecommerceapp.adapter.ViewAllAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    List<ViewAllModel> viewAllModelList;
    ViewAllAdapter viewAllAdapter;

    List<CategoryModel> categoryModelList;
    CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recy_view_all);

        recyclerView1 = findViewById(R.id.recy_view_all);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(viewAllModelList,this);
        recyclerView.setAdapter(viewAllAdapter);

        firebaseFirestore.collection("ViewAll")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ViewAllModel viewAllModel = document.toObject(ViewAllModel.class);
                                viewAllModelList.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
//                                linearLayout.setVisibility(View.VISIBLE);
//                                progressDialog.dismiss();

                            }
                        } else {
                            Toast.makeText(getApplication(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


//        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
//        String type = getIntent().getStringExtra("type");
//
//        if (type != null && type.equalsIgnoreCase("watch")){
//            firebaseFirestore.collection("ViewAll").whereEqualTo("type", "watch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
//                        ViewAllModel allProductsModel = documentSnapshot.toObject(ViewAllModel.class);
//                        viewAllModelList.add(allProductsModel);
//                        viewAllAdapter.notifyDataSetChanged();
////                        recyclerView.setVisibility(android.view.View.VISIBLE);
////                        progressBar.setVisibility(View.GONE);
//                    }
//
//                }
//            });
//        }
        }

    }


