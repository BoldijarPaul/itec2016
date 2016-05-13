package com.itec.order.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.itec.app.R;
import com.itec.order.data.persistance.FullProductRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 5/14/2016.
 */
public class CartAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<FullProductRecord> mProducts = new ArrayList<>();
    private Context mContext;

    public void setProducts(List<FullProductRecord> products) {
        mProducts.clear();
        mProducts.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        FullProductRecord product = mProducts.get(position);
        Glide.with(mContext).load(product.image).into(holder.image);
        holder.title.setText(product.description);
        holder.subtitle.setText(product.category);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void addProduct(FullProductRecord fullProductRecord) {
        mProducts.add(fullProductRecord);
        notifyItemInserted(getItemCount() - 1);
    }
}
