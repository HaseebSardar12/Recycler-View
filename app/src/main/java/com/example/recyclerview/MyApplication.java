package com.example.recyclerview;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Category> categories;
    public static ArrayList<Products> products;

    @Override
    public void onCreate() {
        super.onCreate();
        categories = new ArrayList<>();
        products = new ArrayList<>();
    }
}
