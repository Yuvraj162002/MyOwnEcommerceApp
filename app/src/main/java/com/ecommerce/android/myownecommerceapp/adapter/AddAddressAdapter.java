package com.ecommerce.android.myownecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.android.myownecommerceapp.Model.AddAddressModel;
import com.ecommerce.android.myownecommerceapp.R;

import java.util.List;

public class AddAddressAdapter extends RecyclerView.Adapter<AddAddressAdapter.ViewHolder> {

    List<AddAddressModel> addAddressModels;
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_address,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.city.setText(addAddressModels.get(position).getCity());
        holder.name.setText(addAddressModels.get(position).getName());
        holder.postalcode.setText(addAddressModels.get(position).getPostalcode());
        holder.phno.setText(addAddressModels.get(position).getPhn());
        holder.address.setText(addAddressModels.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return addAddressModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, address , phno , postalcode ,city;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.TV_add_adress);
            address = itemView.findViewById(R.id.TV_address_lane);
            phno =  itemView.findViewById(R.id.TV_phoneNo);
            postalcode = itemView.findViewById(R.id.TV_postalcode);
            city = itemView.findViewById(R.id.TV_city);
        }
    }
}
