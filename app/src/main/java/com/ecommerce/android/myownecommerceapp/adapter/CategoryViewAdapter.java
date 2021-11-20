package com.ecommerce.android.myownecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommerce.android.myownecommerceapp.Model.CategoryViewModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.activities.CategoryView;

import java.util.List;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.ViewHolder> {

    List<CategoryViewModel> categoryViewModelList;
    Context context;

    public CategoryViewAdapter(List<CategoryViewModel> categoryViewModelList, Context context) {
        this.categoryViewModelList = categoryViewModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_view_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(categoryViewModelList.get(position).getImg_url()).into(holder.imageView);
        holder.price.setText(String.valueOf(categoryViewModelList.get(position).getPrice()));
        holder.name.setText(categoryViewModelList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return categoryViewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name , price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IV_nav_view_category);
            name = itemView.findViewById(R.id.name_view_category);
            price = itemView.findViewById(R.id.TV_nav_view_price_cateory);
        }
    }
}
