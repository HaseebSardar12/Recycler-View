package com.example.recyclerview;

public class Category {
    private  String name;
    private String imgUrl;
    private int subCategory;

    public Category() {
    }

    public Category(String name, String imgUrl, int subCategory) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }
}
