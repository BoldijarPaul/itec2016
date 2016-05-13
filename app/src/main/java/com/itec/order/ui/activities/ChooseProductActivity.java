package com.itec.order.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itec.app.R;
import com.itec.order.contracts.ProductsPresenter;
import com.itec.order.contracts.ProductsView;
import com.itec.order.data.models.Product;
import com.itec.order.ui.adapters.ItemClickSupport;
import com.itec.order.ui.adapters.ProductsAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChooseProductActivity extends BaseActivity implements ProductsView {

    public static final String KEY_PRODUCT_ID = "KEYPRODID";
    @Bind(R.id.choose_product_recycler)
    RecyclerView mRecyclerView;

    public static Intent createIntent(Context context) {
        return new Intent(context, ChooseProductActivity.class);
    }

    private ProductsAdapter mProductsAdapter;
    private ProductsPresenter mProductsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductsAdapter = new ProductsAdapter();
        mRecyclerView.setAdapter(mProductsAdapter);

        mProductsPresenter = new ProductsPresenter(this);
        mProductsPresenter.loadProducts();
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                productChoosen(mProductsAdapter.getProduct(position));
            }
        });
    }

    private void productChoosen(Product product) {
        Intent data = new Intent();
        data.putExtra(KEY_PRODUCT_ID, product.id);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    protected void onDestroy() {
        ItemClickSupport.removeFrom(mRecyclerView);
        super.onDestroy();
    }

    @Override
    public void showProducts(List<Product> products) {
        mProductsAdapter.setProducts(products);
    }
}
