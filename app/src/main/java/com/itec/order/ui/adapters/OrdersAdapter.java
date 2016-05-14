package com.itec.order.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itec.app.R;
import com.itec.order.data.events.LogoutEvent;
import com.itec.order.data.persistance.OrderProductRecord;
import com.itec.order.data.persistance.OrderRecord;
import com.itec.order.ui.app.BaseApp;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Paul on 5/14/2016.
 */
public class OrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 1, TYPE_ORDER = 2;
    private Context mContext;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault());

    private List<OrderRecord> mOrders;

    public OrdersAdapter() {
        mOrders = OrderRecord.listAll(OrderRecord.class);
        for (OrderRecord orderRecord : mOrders) {
            orderRecord.products = OrderRecord.find(OrderProductRecord.class, "order_id = ?", orderRecord.orderId + "");
        }
//        mOrders = new ArrayList<>();
//        for (int i = 1; i <= 5; i++) {
//            List<CurrentCartProduct> products = new ArrayList<>();
//            for (int j = 0; j <= i; j++)
//                products.add(new CurrentCartProduct("des", 12, ImageUtils.getRandomImage(), 1, "lala", 5));
//            OrderRecord orderRecord = new OrderRecord(1, products);
//            mOrders.add(orderRecord);
//        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (viewType == TYPE_ORDER) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
            return new OrderViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.profile_header, parent, false);
            return new HeaderViewHolder(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ORDER;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ORDER) {
            OrderRecord order = mOrders.get(position - 1);
            OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
            orderViewHolder.date.setText(mContext.getString(R.string.date) + mSimpleDateFormat.format(order.date));
            int price = 0;
            for (OrderProductRecord productRecord : order.products) {
                price += productRecord.productId * productRecord.amount;
            }
            orderViewHolder.price.setText(price + " " + mContext.getString(R.string.currency));
        } else {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.mEmail.setText(BaseApp.getEmail().get());
            headerViewHolder.mLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new LogoutEvent());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mOrders.size() + 1;
    }
}
