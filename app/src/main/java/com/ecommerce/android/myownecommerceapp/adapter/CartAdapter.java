package com.ecommerce.android.myownecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommerce.android.myownecommerceapp.Model.CartModel;
import com.ecommerce.android.myownecommerceapp.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<CartModel> cartModelList;
    int totalAmount = 0;

    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(cartModelList.get(position).getProductName());
        holder.price.setText(cartModelList.get(position).getProductPrice());
        holder.quantity.setText(cartModelList.get(position).getTotalQuantity());
        holder.totalprice.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));
        holder.date.setText(cartModelList.get(position).getCurrentDate());
        holder.time.setText(cartModelList.get(position).getCurrentTime());

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

             TextView name;
             TextView price;
             TextView quantity;
             TextView totalprice;
             TextView date;
             TextView time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_my_cart);
            price = itemView.findViewById(R.id.price_my_cart);
            quantity = itemView.findViewById(R.id.totalquantity_cart);
            totalprice = itemView.findViewById(R.id.total_price_cart);
            date = itemView.findViewById(R.id.current_date_cart);
            time = itemView.findViewById(R.id.time_cart);
        }
    }
}
