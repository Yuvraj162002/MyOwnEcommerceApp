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
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.activities.ProductDetailedActivity;

import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {


    Context context;
   List<New_Product_model> new_product_modelList;

    public NewProductAdapter(Context context, List<New_Product_model> new_product_modelList) {
        this.context = context;
        this.new_product_modelList = new_product_modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(new_product_modelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(new_product_modelList.get(position).getTitle());
        holder.text.setText(new_product_modelList.get(position).getText());
        holder.price.setText(String.valueOf(new_product_modelList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, ProductDetailedActivity.class);
                intent.putExtra("type",new_product_modelList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return new_product_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name , text , price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IV_new_product);
            name = itemView.findViewById(R.id.TV_name_product);
            text = itemView.findViewById(R.id.TV_new);
            price = itemView.findViewById(R.id.Price_new_product);
        }
    }
}
