package com.ecommerce.android.myownecommerceapp.Model;

import java.io.Serializable;

public class New_Product_model implements Serializable {

    int Price;
    String img_url;
    String text;
    String title;
    String type;
    String rating;

    public New_Product_model() {
    }

    public New_Product_model(int price, String img_url, String text, String title, String type, String rating) {
        Price = price;
        this.img_url = img_url;
        this.text = text;
        this.title = title;
        this.type = type;
        this.rating = rating;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
