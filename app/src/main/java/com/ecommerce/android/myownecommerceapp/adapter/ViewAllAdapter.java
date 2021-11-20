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
import com.ecommerce.android.myownecommerceapp.Model.ViewAllModel;
import com.ecommerce.android.myownecommerceapp.R;
import com.ecommerce.android.myownecommerceapp.activities.ProductDetailedActivity;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

  List<ViewAllModel> viewAllModelList;
  Context context;

    public ViewAllAdapter(List<ViewAllModel> viewAllModelList, Context context) {
        this.viewAllModelList = viewAllModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_view_all,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(viewAllModelList.get(position).getName());
       holder.price.setText(String.valueOf(viewAllModelList.get(position).getPrice()));


       holder.imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, ProductDetailedActivity.class);
               intent.putExtra("Viewtype",viewAllModelList.get(holder.getAdapterPosition()));
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name , price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IV_nav_view_all);
            name = itemView.findViewById(R.id.name_view_all);
            price = itemView.findViewById(R.id.TV_nav_view_price);
        }
    }
}
