package com.example.recyclerview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvCategories;
    LinearLayoutManager llmanager;
    GridLayoutManager glmanager;
    CategoryAdapter adapter;
    FloatingActionButton fabAdd;

    RecyclerView rvProducts;
    ProductAdapter ProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirmation = new AlertDialog.Builder(MainActivity.this);
                confirmation.setTitle("Which one do you want to add?");
                confirmation.setPositiveButton("Add Category", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder addCategory = new AlertDialog.Builder(MainActivity.this);
                        addCategory.setTitle("Add New Category...");
                        View view = LayoutInflater.from(MainActivity.this)
                                .inflate(R.layout.form_to_add_category,null,false);
                        addCategory.setView(view);

                        EditText etName, etSubCategoryCount, etImgUrl;
                        etName = view.findViewById(R.id.etName);
                        etSubCategoryCount = view.findViewById(R.id.etSubcategory);
                        etImgUrl = view.findViewById(R.id.etImageUrl);

                        addCategory.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Category category = new Category(etName.getText().toString().trim(), etImgUrl.getText().toString().trim(),
                                        Integer.parseInt(etSubCategoryCount.getText().toString()));
                                MyApplication.categories.add(category);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Category added Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        addCategory.show();
                    }
                });


                confirmation.setNegativeButton("Add Product", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder addProduct = new AlertDialog.Builder(MainActivity.this);
                        addProduct.setTitle("Add New Product...");
                        View view = LayoutInflater.from(MainActivity.this)
                                .inflate(R.layout.form_to_add_product,null,false);
                        addProduct.setView(view);

                        EditText etPName, etPrice, etRating, etImgUrl;
                        etPName = view.findViewById(R.id.etPName);
                        etPrice = view.findViewById(R.id.etPrice);
                        etImgUrl = view.findViewById(R.id.etImageUrl);
                        etRating = view.findViewById(R.id.etRating);

                        addProduct.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Products product = new Products(etPName.getText().toString().trim(),Float.parseFloat(etRating.getText().toString()),
                                        Float.parseFloat(etPrice.getText().toString()), etImgUrl.getText().toString().trim());
                                MyApplication.products.add(product);
                                ProductAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Product added Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        addProduct.show();
                    }
                });
                confirmation.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void init()
    {
        rvCategories = findViewById(R.id.rvCategories);
        fabAdd = findViewById(R.id.fabAdd);
        llmanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCategories.setLayoutManager(llmanager);
        adapter = new CategoryAdapter(this, MyApplication.categories);
        rvCategories.setAdapter(adapter);

        rvProducts = findViewById(R.id.rvProducts);
        glmanager = new GridLayoutManager(this, 2);
        rvProducts.setLayoutManager(glmanager);
        ProductAdapter = new ProductAdapter(this, MyApplication.products);
        rvProducts.setAdapter(ProductAdapter);
    }
}