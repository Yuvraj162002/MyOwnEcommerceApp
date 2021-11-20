package com.ecommerce.android.myownecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.ecommerce.android.myownecommerceapp.Model.New_Product_model;
import com.ecommerce.android.myownecommerceapp.Model.PopularProductModel;
import com.ecommerce.android.myownecommerceapp.Model.ViewAllModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailedActivity extends AppCompatActivity {


    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    //// New_Product........
    ImageView imageView , add , minus ;
    Button add_to_cart , buy_now ;
    TextView price  , desp , name , rating , quantity;

    int totalQuantity = 1;
    int totalprice = 0;

    RatingBar ratingBar;

  androidx.appcompat.widget.Toolbar toolbar;

    New_Product_model new_product_model =  null;

    PopularProductModel popularProductModel = null;

    ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);

        toolbar = findViewById(R.id.toolbar_product);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.IV_product_detail);
        add_to_cart = findViewById(R.id.add_to_cart_detail);
        buy_now = findViewById(R.id.buy_now_detail);
        add = findViewById(R.id.plus_product);
        minus = findViewById(R.id.minus_product);
        price = findViewById(R.id.actual_price_product);
        desp = findViewById(R.id.desp_product);
        name = findViewById(R.id.TV_product_detail);

        quantity = findViewById(R.id.quantity_product);

        rating = findViewById(R.id.Rating_NO_product_detail);
        ratingBar = findViewById(R.id.ratingbar_product_detail);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();



//        getSupportActionBar().hide();

        //// New_Product.......
        final Object object = getIntent().getSerializableExtra("type");

        if (object instanceof New_Product_model) {
            new_product_model = (New_Product_model) object;
        }


        if (new_product_model != null) {
            Glide.with(getApplicationContext()).load(new_product_model.getImg_url()).into(imageView);
            rating.setText(new_product_model.getRating());
            name.setText(new_product_model.getTitle());
            price.setText(String.valueOf(new_product_model.getPrice()));

            totalprice =    new_product_model.getPrice() *totalQuantity;

        }

        /// Popular_Proudct.........

        final Object objectnew = getIntent().getSerializableExtra("type");

        if (objectnew instanceof PopularProductModel) {
            popularProductModel = (PopularProductModel) objectnew;
        }


        if (popularProductModel != null) {
            Glide.with(getApplicationContext()).load(popularProductModel.getImg_url()).into(imageView);
            rating.setText(popularProductModel.getRating());
            name.setText(popularProductModel.getName());
            price.setText(String.valueOf(popularProductModel.getPrice()));
            totalprice =    popularProductModel.getPrice() *totalQuantity;

        }

        /// View All...

        final Object objectnewreal = getIntent().getSerializableExtra("Viewtype");

        if (objectnewreal instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) objectnewreal;
        }


        if (viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(imageView);
            rating.setText(viewAllModel.getRating());
            name.setText(viewAllModel.getName());
            price.setText(String.valueOf(viewAllModel.getPrice()));

            totalprice = viewAllModel.getPrice() * totalQuantity;

        }

        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(ProductDetailedActivity.this , AddressActivity.class);

             if (new_product_model!=null){
                 intent.putExtra("item",new_product_model);
             }
             if (viewAllModel !=null){
                 intent.putExtra("item",viewAllModel);
             }
             if (popularProductModel!=null){
                 intent.putExtra("item",popularProductModel);
             }
             startActivity(intent);
            }
        });
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtoCart();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if (new_product_model != null){
                        totalprice = (new_product_model.getPrice()) * totalQuantity;
                    }
                    if ( popularProductModel != null){
                        totalprice = (popularProductModel.getPrice()) * totalQuantity;
                    }
                    if (viewAllModel != null){
                        totalprice = (viewAllModel.getPrice())*totalQuantity;
                    }

                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));


                }
            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void addtoCart() {

       String currentTime , currentdate;

        Calendar calendar = Calendar.getInstance();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM, dd,yyyy");
        currentdate = simpleDateFormat.format(calendar.getTime());


        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss a");
        currentTime = simpleDateFormat1.format(calendar.getTime());

        final HashMap<String,Object> cartmap = new HashMap<>();
        cartmap.put("ProductName",popularProductModel.getName());
        //  Object product_name = cartmap.put("Product Name", AllProductsModel.getName());
        cartmap.put("ProductPrice", price.getText().toString());
        //  cartmap.put("Product Price",price.getText().toString());
        cartmap.put("currentDate",currentdate);
        cartmap.put("currentTime",currentTime);
        cartmap.put("TotalQuantity",quantity.getText().toString());
        cartmap.put("TotalPrice",totalprice);
//        cartmap.put("TotalQuantity",quantity.getText().toString());
//        cartmap.put("totalprice",totalprice);

        firebaseFirestore.collection("CurrentUser").document(firebaseAuth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartmap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ProductDetailedActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}