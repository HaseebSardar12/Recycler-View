package com.example.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context Pcontext;
    ArrayList<Products> products;
    public ProductAdapter(Context context, ArrayList<Products> products) {
        Pcontext = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product_item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products singleProduct = products.get(position);
        holder.tvProductName.setText(singleProduct.getProductName());
        holder.tvProductRating.setText(singleProduct.getProductRating()+"");
        holder.tvProductPrice.setText(singleProduct.getPrice()+"");
        if(singleProduct.getProductImgUrl().equals("shirt"))
        {
            holder.ivProductImage.setImageResource(R.drawable.shirt);
        }
        if(singleProduct.getProductImgUrl().equals("shoes"))
        {
            holder.ivProductImage.setImageResource(R.drawable.shoe);
        }
        if(singleProduct.getProductImgUrl().equals("ring"))
        {
            holder.ivProductImage.setImageResource(R.drawable.ring);
        }
        if(singleProduct.getProductImgUrl().equals("pants"))
        {
            holder.ivProductImage.setImageResource(R.drawable.pants);
        }
        if(singleProduct.getProductImgUrl().equals("bracelet"))
        {
            holder.ivProductImage.setImageResource(R.drawable.bracelet);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder update = new AlertDialog.Builder(Pcontext);
                update.setTitle("Update or Delete Product");
                View view = LayoutInflater.from(Pcontext).inflate(R.layout.update_product_form, null,false);
                update.setView(view);
                EditText etPName, etPrice, etRating, etImgUrl;
                etPName = view.findViewById(R.id.etPName);
                etPrice = view.findViewById(R.id.etPrice);
                etImgUrl = view.findViewById(R.id.etImageUrl);
                etRating = view.findViewById(R.id.etRating);

                etPName.setText(singleProduct.getProductName());
                etPrice.setText(singleProduct.getPrice()+"");
                etRating.setText(singleProduct.getProductRating()+"");
                etImgUrl.setText(singleProduct.getProductImgUrl());

                update.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        singleProduct.setProductName(etPName.getText().toString().trim());
                        singleProduct.setProductRating(Float.parseFloat(etRating.getText().toString()));
                        singleProduct.setPrice(Float.parseFloat(etPrice.getText().toString().trim()));
                        singleProduct.setProductImgUrl(etImgUrl.getText().toString().trim());

                        Toast.makeText(Pcontext, "Product Updated Successfully!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                update.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        products.remove(holder.getAdapterPosition());
                        Toast.makeText(Pcontext, "Product Deleted Successfully!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                update.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivProductImage;
        TextView tvProductName, tvProductPrice, tvProductRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvPrice);
            tvProductRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
