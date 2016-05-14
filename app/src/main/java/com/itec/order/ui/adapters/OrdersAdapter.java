package com.itec.order.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itec.app.R;
import com.itec.order.data.persistance.OrderRecord;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Paul on 5/14/2016.
 */
public class OrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 1, TYPE_ORDER = 2;
    private Context mContext;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd.MM.YYYY hh:mm", Locale.getDefault());

    private List<OrderRecord> mOrders;

    public OrdersAdapter() {
        mOrders = OrderRecord.listAll(OrderRecord.class);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (viewType == TYPE_ORDER) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
            return new OrderViewHolder(itemView);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ORDER;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ORDER) {
            OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
            orderViewHolder.date.setText(mSimpleDateFormat.format(mOrders.get(position).date));
        }
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }
}
