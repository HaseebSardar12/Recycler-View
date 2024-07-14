package com.example.recyclerview;

public class Products {
    private String productName;
    private float productRating;
    private float price;
    private String productImgUrl;

    public Products() {
    }

    public Products(String productName, float productRating, float price, String productImgUrl) {
        this.productName = productName;
        this.productRating = productRating;
        this.price = price;
        this.productImgUrl = productImgUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductRating() {
        return productRating;
    }

    public void setProductRating(float productRating) {
        this.productRating = productRating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }
}
