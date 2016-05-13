package com.itec.order.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.itec.app.R;
import com.itec.order.contracts.ProductsPresenter;
import com.itec.order.contracts.ProductsView;
import com.itec.order.data.models.Product;
import com.itec.order.ui.adapters.ProductsAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChooseProductActivity extends AppCompatActivity implements ProductsView {

    @Bind(R.id.choose_product_recycler)
    RecyclerView mRecyclerView;

    private ProductsAdapter mProductsAdapter;
    private ProductsPresenter mProductsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);
        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.choose_product_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProductsAdapter = new ProductsAdapter();
        mRecyclerView.setAdapter(mProductsAdapter);

        mProductsPresenter = new ProductsPresenter(this);
        mProductsPresenter.loadProducts();
    }

    @Override
    public void showProducts(List<Product> products) {
        mProductsAdapter.setProducts(products);
    }
}
