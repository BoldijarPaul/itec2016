package com.itec.order.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.itec.app.R;
import com.itec.order.data.ImageUtils;
import com.itec.order.data.persistance.FullProductRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 5/13/2016.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context mContext;
    private List<FullProductRecord> mProducts = new ArrayList<>();
    private List<FullProductRecord> mVisibleProducts = new ArrayList<>();

    public void setProducts(List<FullProductRecord> products) {
        mProducts.clear();
        mProducts.addAll(products);
        mVisibleProducts.clear();
        mVisibleProducts.addAll(products);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        mVisibleProducts.clear();
        for (FullProductRecord fullProductRecord : mProducts) {
            if (fullProductRecord.description.toLowerCase().contains(query.toLowerCase())) {
                mVisibleProducts.add(fullProductRecord);
            }
        }
        notifyDataSetChanged();
    }

    public void removeFilter() {
        mVisibleProducts.clear();
        mVisibleProducts.addAll(mProducts);
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
        FullProductRecord product = mVisibleProducts.get(position);
        Glide.with(mContext).load(ImageUtils.getImageForInt(product.productId)).into(holder.image);
        holder.title.setText(product.description);
        holder.subtitle.setText(product.category);
    }

    public FullProductRecord getProduct(int position) {
        return mVisibleProducts.get(position);
    }

    @Override
    public int getItemCount() {
        return mVisibleProducts.size();
    }
}
