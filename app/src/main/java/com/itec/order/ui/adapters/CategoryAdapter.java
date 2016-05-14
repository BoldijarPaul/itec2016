package com.itec.order.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.itec.app.R;
import com.itec.order.data.ImageUtils;
import com.itec.order.data.models.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjz on 5/14/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private Context mContext;
    private List<Category> mCategories=new ArrayList<Category>();

    public CategoryAdapter() {
        for (int i = 0; i < 20; i++) {
            Category category = new Category();
            category.checked = false;
            category.description = "ceva";
            category.image = ImageUtils.getRandomImage();
            mCategories.add(category);
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category category = mCategories.get(position);
        Glide.with(mContext).load(category.image).into(holder.categoryImage);
        holder.categoryText.setText(category.description);
        holder.categoryCheck.setChecked(category.checked);
        holder.categoryCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                category.checked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }
}
