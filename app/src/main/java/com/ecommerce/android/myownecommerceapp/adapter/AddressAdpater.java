package com.ecommerce.android.myownecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommerce.android.myownecommerceapp.Model.AddressModel;
import com.ecommerce.android.myownecommerceapp.R;

import java.util.List;

public class AddressAdpater extends RecyclerView.Adapter<AddressAdpater.ViewHolder> {

    List<AddressModel> addressModelList;
    Context context;
    SelectedAddress selectedAddress;
    private RadioButton SelectedRadioBtn;

    public AddressAdpater(List<AddressModel> addressModelList, Context context, SelectedAddress selectedAddress) {
        this.addressModelList = addressModelList;
        this.context = context;
        this.selectedAddress = selectedAddress;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_address,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.address.setText(addressModelList.get(holder.getAdapterPosition()).getUserAddress());
         holder.radioButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 for (AddressModel addressModel : addressModelList){
                     addressModel.setSelected(false);
                 }
                 addressModelList.get(holder.getAdapterPosition()).setSelected(true);

                 if (SelectedRadioBtn!=null){
                     SelectedRadioBtn.setChecked(false);
                 }

                 SelectedRadioBtn = (RadioButton)  v;
                 SelectedRadioBtn.setChecked(true);
                 selectedAddress.setAddress(addressModelList.get(holder.getAdapterPosition()).getUserAddress());
             }
         });
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView address;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.Address_nav_address);
            radioButton = itemView.findViewById(R.id.radioButton);
        }
    }

    public interface SelectedAddress {
        void setAddress(String address);
    }
}
