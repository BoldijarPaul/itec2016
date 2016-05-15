package com.itec.order.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.itec.app.R;
import com.itec.order.data.BillProduct;
import com.itec.order.data.persistance.FullProductRecord;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 5/15/2016.
 */
public class PayAdapter extends RecyclerView.Adapter<PayViewHolder> {

    private List<BillProduct> mBillProductList = new ArrayList<>();

    public void setProducts(List<BillProduct> products) {
        mBillProductList.clear();
        mBillProductList.addAll(products);
        for (BillProduct billProduct : products) {
            List<FullProductRecord> records = FullProductRecord.find(FullProductRecord.class, "product_id = ?", billProduct.productId + "");
            if (records.size() > 0) {
                billProduct.image = records.get(0).image;
                billProduct.category = records.get(0).category;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public PayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PayViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(PayViewHolder holder, int position) {
        final BillProduct billProduct = mBillProductList.get(position);
        holder.title.setText(billProduct.productName);
        holder.amount.setText("Quantity: " + billProduct.quantity);
        holder.price.setText("" + billProduct.price);
        holder.subtitle.setText(billProduct.category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(billProduct);
            }
        });
        Glide.with(holder.image.getContext()).load(billProduct.image).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mBillProductList.size();
    }
}
