package com.itec.order.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.itec.app.R;
import com.itec.order.data.ImageUtils;
import com.itec.order.data.persistance.CurrentCartProduct;
import com.itec.order.data.persistance.FullProductRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 5/14/2016.
 */
public class CartAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<CurrentCartProduct> mProducts;
    private Context mContext;
    private CurrentCartProduct mLastCartDeleted;

    public CartAdapter() {
        try {
            mProducts = CurrentCartProduct.listAll(CurrentCartProduct.class);
        } catch (Exception e) {
            mProducts = new ArrayList<>();
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        CurrentCartProduct product = mProducts.get(position);
        Glide.with(mContext).load(ImageUtils.getImageForInt(product.productId)).into(holder.image);
        holder.title.setText(product.description);
        holder.subtitle.setText(product.category);
        if (product.amount <= 1) {
            holder.amount.setText(null);
        } else {
            holder.amount.setText(mContext.getString(R.string.quantity) + product.amount);
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void addProduct(FullProductRecord fullProductRecord) {
        for (int i = 0; i < mProducts.size(); i++) {
            CurrentCartProduct product = mProducts.get(i);
            if (product.productId == fullProductRecord.productId) {
                product.amount++;
                product.save();
                notifyItemChanged(i);
                return;
            }
        }
        CurrentCartProduct currentCartProduct = new CurrentCartProduct(fullProductRecord);
        currentCartProduct.save();
        mProducts.add(currentCartProduct);

        notifyItemInserted(getItemCount() - 1);
    }

    public boolean removeProduct(int adapterPosition) {
        CurrentCartProduct currentCartProduct = mProducts.get(adapterPosition);
        if (currentCartProduct.amount == 1) {
            currentCartProduct.delete();
            mLastCartDeleted = currentCartProduct;
            mProducts.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
            return true;
        } else {
            currentCartProduct.amount--;
            currentCartProduct.save();
            notifyItemChanged(adapterPosition);
            return false;
        }
    }

    public void undo() {
        if (mLastCartDeleted != null) {
            mLastCartDeleted.save();
            mProducts.add(mLastCartDeleted);
            notifyItemInserted(getItemCount() - 1);
            mLastCartDeleted = null;
        }
    }
}
