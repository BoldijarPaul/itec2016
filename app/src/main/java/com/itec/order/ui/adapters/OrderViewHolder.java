package com.itec.order.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.itec.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Paul on 5/14/2016.
 */
public class OrderViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.order_date)
    TextView date;
    @Bind(R.id.order_price)
    TextView price;

    public OrderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
