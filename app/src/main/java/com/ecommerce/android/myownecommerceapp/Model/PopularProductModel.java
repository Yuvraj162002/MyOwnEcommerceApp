package com.ecommerce.android.myownecommerceapp.Model;

import java.io.Serializable;

public class PopularProductModel implements Serializable {
    String name;
    int price;
    String type;
    String img_url;
    String rating;

    public PopularProductModel() {
    }

    public PopularProductModel(String name, int price, String type, String img_url, String rating) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.img_url = img_url;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
