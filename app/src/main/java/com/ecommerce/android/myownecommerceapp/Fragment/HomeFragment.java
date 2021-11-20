package com.ecommerce.android.myownecommerceapp.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.ecommerce.android.myownecommerceapp.Model.CategoryModel;
import com.ecommerce.android.myownecommerceapp.Model.New_Product_model;
import com.ecommerce.android.myownecommerceapp.Model.PopularProductModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.activities.ViewAllActivity;
import com.ecommerce.android.myownecommerceapp.adapter.CategoryAdapter;
import com.ecommerce.android.myownecommerceapp.adapter.NewProductAdapter;
import com.ecommerce.android.myownecommerceapp.adapter.PopularProductAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    LinearLayout linearLayout;

    TextView viewAll;

    /// Category........
    CategoryAdapter categoryAdapter;
   List<CategoryModel> categoryModelList;
   RecyclerView category;


   /// New Product....
    NewProductAdapter newProductAdapter;
    List<New_Product_model> new_product_modelList;
    RecyclerView recyclerView;


    //// Popular Product....

    PopularProductAdapter popularProductAdapter;
    List<PopularProductModel> popularProductModelList;
    RecyclerView popularRecy;


    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        // ImageSlider....
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1,"Discount On Shoe Item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Discount On Perfume",ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"70% OFF",ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);

        firebaseFirestore = FirebaseFirestore.getInstance();

        viewAll = view.findViewById(R.id.newProducts_see_all);

        category = view.findViewById(R.id.rec_category);
        progressDialog = new ProgressDialog(getActivity());
        recyclerView = view.findViewById(R.id.new_product_rec);
        popularRecy = view.findViewById(R.id.popular_rec);
        linearLayout = view.findViewById(R.id.home_layout);


        linearLayout.setVisibility(View.GONE);
////    Progress dialog......
      progressDialog.setTitle("Welcome to my Ecommerce App");
      progressDialog.setMessage("please wait....");
      progressDialog.setCanceledOnTouchOutside(false);
      progressDialog.show();



//// Category layout Manager......
        category.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryModelList,getActivity());
        category.setAdapter(categoryAdapter);
        firebaseFirestore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                               progressDialog.dismiss();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//// New product layout Manager.....
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        new_product_modelList = new ArrayList<>();
        newProductAdapter = new NewProductAdapter(getActivity(),new_product_modelList);
        recyclerView.setAdapter(newProductAdapter);
        firebaseFirestore.collection("NewProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                New_Product_model new_product_model = document.toObject(New_Product_model.class);
                                new_product_modelList.add(new_product_model);
                                newProductAdapter.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //// Popular layout Manager......

      ///  (When we use grid view instead of linear view)
        popularRecy.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularProductModelList = new ArrayList<>();
        popularProductAdapter = new PopularProductAdapter(getActivity(),popularProductModelList);
        popularRecy.setAdapter(popularProductAdapter);
        firebaseFirestore.collection("PopularProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductModel popularProductModel = document.toObject(PopularProductModel.class);
                                popularProductModelList.add(popularProductModel);
                                popularProductAdapter.notifyDataSetChanged();
                            //    progressBar.setVisibility(View.INVISIBLE);

                            }
                        } else {

                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ViewAllActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}