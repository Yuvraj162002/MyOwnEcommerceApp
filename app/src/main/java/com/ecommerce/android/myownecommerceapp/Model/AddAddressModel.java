package com.ecommerce.android.myownecommerceapp.Model;

public class AddAddressModel {

    String name;
    String address;
    String phn;
    String city;
    String postalcode;

    public AddAddressModel() {
    }

    public AddAddressModel(String name, String address, String phn, String city, String postalcode) {
        this.name = name;
        this.address = address;
        this.phn = phn;
        this.city = city;
        this.postalcode = postalcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
}
