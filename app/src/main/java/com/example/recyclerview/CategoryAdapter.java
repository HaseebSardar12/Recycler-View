package com.example.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context c;
    ArrayList<Category> categories;
    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        c = context;
        this.categories = categories;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_design, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category singleCategory = categories.get(position);
        holder.tvCategoryCount.setText(singleCategory.getSubCategory()+"");
        holder.tvName.setText(singleCategory.getName());
        if(singleCategory.getImgUrl().equals("clothes"))
        {
            holder.ivCategoryImage.setImageResource(R.drawable.shirt);
        }
        else if(singleCategory.getImgUrl().equals("pants"))
        {
            holder.ivCategoryImage.setImageResource(R.drawable.pants);
        }
        else if(singleCategory.getImgUrl().equals("ring"))
        {
            holder.ivCategoryImage.setImageResource(R.drawable.ring);
        }
        else if(singleCategory.getImgUrl().equals("bracelet"))
        {
            holder.ivCategoryImage.setImageResource(R.drawable.bracelet);
        }
        else if(singleCategory.getImgUrl().equals("shoes"))
        {
            holder.ivCategoryImage.setImageResource(R.drawable.shoe);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                android.app.AlertDialog.Builder update = new AlertDialog.Builder(c);
                update.setTitle("Update or Delete Category");
                View view = LayoutInflater.from(c).inflate(R.layout.update_category_form, null,false);
                update.setView(view);
                EditText etName, etSubCategoryCount, etImgUrl;
                etName = view.findViewById(R.id.etName);
                etSubCategoryCount = view.findViewById(R.id.etSubcategory);
                etImgUrl = view.findViewById(R.id.etImageUrl);

                etName.setText(singleCategory.getName());
                etSubCategoryCount.setText(singleCategory.getSubCategory()+"");
                etImgUrl.setText(singleCategory.getImgUrl());

                update.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        singleCategory.setName(etName.getText().toString().trim());
                        singleCategory.setSubCategory(Integer.parseInt(etSubCategoryCount.getText().toString()));
                        singleCategory.setImgUrl(etImgUrl.getText().toString().trim());

                        Toast.makeText(c, "Category Updated Successfully!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                update.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        categories.remove(holder.getAdapterPosition());
                        Toast.makeText(c, "Category Deleted Successfully!", Toast.LENGTH_SHORT).show();
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
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivCategoryImage;
        TextView tvCategoryCount, tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryImage = itemView.findViewById(R.id.ivCategoryImage);
            tvName = itemView.findViewById(R.id.tvCategoryName);
            tvCategoryCount = itemView.findViewById(R.id.tvSubcategoryCount);
        }
    }
}
