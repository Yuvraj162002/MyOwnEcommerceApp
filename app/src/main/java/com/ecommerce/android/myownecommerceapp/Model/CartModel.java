package com.ecommerce.android.myownecommerceapp.Model;

public class CartModel {

    String ProductName;
    String ProductPrice;
    int TotalPrice;
   String TotalQuantity;
   String currentDate;
   String currentTime;
   String documentId;

    public CartModel() {
    }

    public CartModel(String productName, String productPrice, int totalPrice, String totalQuantity, String currentDate, String currentTime) {
        ProductName = productName;
        ProductPrice = productPrice;
        TotalPrice = totalPrice;
        TotalQuantity = totalQuantity;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }
}
