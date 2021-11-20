package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ecommerce.android.myownecommerceapp.Model.CategoryViewModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.adapter.CategoryViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryView extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    CategoryViewAdapter categoryViewAdapter;
    List<CategoryViewModel> categoryViewModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recy_category_view);
        String type = getIntent().getStringExtra("type");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryViewModelList = new ArrayList<>();
        categoryViewAdapter = new CategoryViewAdapter(categoryViewModelList,this);
        recyclerView.setAdapter(categoryViewAdapter);

        if (type != null && type.equalsIgnoreCase("watches")){
            firebaseFirestore.collection("CategoryNew").whereEqualTo("type","watches")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        CategoryViewModel categoryViewModel = documentSnapshot.toObject(CategoryViewModel.class);
                        categoryViewModelList.add(categoryViewModel);
                        categoryViewAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}