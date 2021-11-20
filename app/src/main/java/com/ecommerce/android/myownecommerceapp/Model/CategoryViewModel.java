package com.ecommerce.android.myownecommerceapp.Model;

import java.io.Serializable;

public class CategoryViewModel implements Serializable {

    String name;
    String img_url;
    String type;
    int price;

    public CategoryViewModel() {
    }

    public CategoryViewModel(String name, String img_url, String type, int price) {
        this.name = name;
        this.img_url = img_url;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
