package com.itec.order.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itec.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Paul on 5/15/2016.
 */
public class PayViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.product_price)
    TextView price;
    @Bind(R.id.product_title)
    TextView title;
    @Bind(R.id.product_amount)
    TextView amount;
    @Bind(R.id.product_subtitle)
    TextView subtitle;
    @Bind(R.id.product_image)
    ImageView image;

    public PayViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
