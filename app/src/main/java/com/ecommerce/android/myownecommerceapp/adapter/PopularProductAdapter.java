package com.ecommerce.android.myownecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommerce.android.myownecommerceapp.Model.New_Product_model;
import com.ecommerce.android.myownecommerceapp.Model.PopularProductModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.activities.ProductDetailedActivity;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder> {

    Context context;
    List<PopularProductModel> popularProductModelList;

    public PopularProductAdapter(Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(popularProductModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(popularProductModelList.get(position).getName());
        holder.price.setText(String.valueOf(popularProductModelList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context , ProductDetailedActivity.class);
                intent.putExtra("type",popularProductModelList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name , price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IV_popular_products);
            name = itemView.findViewById(R.id.TV_popular_product);
            price = itemView.findViewById(R.id.Price_popular_product);
        }
    }
}
