package com.itec.order.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.itec.app.R;
import com.itec.order.data.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 5/13/2016.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context mContext;
    private List<Product> mProducts = new ArrayList<>();

    public void setProducts(List<Product> products) {
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
        Product product = mProducts.get(position);
        Glide.with(mContext).load(product.image).into(holder.image);
        holder.title.setText(product.description);
        holder.subtitle.setText("Category id: " + product.categoryId);
    }

    public Product getProduct(int position) {
        return mProducts.get(position);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
