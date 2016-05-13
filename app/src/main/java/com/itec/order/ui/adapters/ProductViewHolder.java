package com.itec.order.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itec.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Paul on 5/13/2016.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.product_title)
    TextView title;
    @BindView(R.id.product_subtitle)
    TextView subtitle;
    @BindView(R.id.product_image)
    ImageView image;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
