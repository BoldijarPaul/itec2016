package com.itec.order.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.itec.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bjz on 5/14/2016.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder{
    @Bind(R.id.category_image)
    ImageView categoryImage;
    @Bind(R.id.category_text)
    TextView categoryText;
    @Bind(R.id.category_checkbox)
    CheckBox categoryCheck;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
