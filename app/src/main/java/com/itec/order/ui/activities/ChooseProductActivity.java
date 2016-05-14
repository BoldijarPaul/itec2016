package com.itec.order.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.itec.app.R;
import com.itec.order.contracts.ProductsPresenter;
import com.itec.order.contracts.ProductsView;
import com.itec.order.data.persistance.FullProductRecord;
import com.itec.order.ui.adapters.ItemClickSupport;
import com.itec.order.ui.adapters.ProductsAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChooseProductActivity extends BaseActivity implements ProductsView, SearchView.OnQueryTextListener {

    public static final String KEY_PRODUCT_ID = "KEYPRODID";
    @Bind(R.id.choose_product_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.choose_product_toolbar)
    Toolbar mToolbar;
    private SearchView mSearchView;
    private SimpleCursorAdapter mSimpleCursorAdapter;

    public static Intent createIntent(Context context) {
        return new Intent(context, ChooseProductActivity.class);
    }

    private ProductsAdapter mProductsAdapter;
    private ProductsPresenter mProductsPresenter;

    private static final String[] SUGGESTIONS = {
            "Bauru", "Sao Paulo", "Rio de Janeiro",
            "Bahia", "Mato Grosso", "Minas Gerais",
            "Tocantins", "Rio Grande do Sul"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);
        ButterKnife.bind(this);

        setupToolbar();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductsAdapter = new ProductsAdapter();
        mRecyclerView.setAdapter(mProductsAdapter);

        mProductsPresenter = new ProductsPresenter(this);
        mProductsPresenter.loadAllProducts();
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                productChoosen(mProductsAdapter.getProduct(position));
            }
        });
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choose_product_menu, menu);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setOnQueryTextListener(this);

        final String[] from = new String[]{"asd"};
        final int[] to = new int[]{android.R.id.text1};
        mSimpleCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_dropdown_item_1line,
                null,
                from,
                to,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mSearchView.setSuggestionsAdapter(mSimpleCursorAdapter);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void productChoosen(FullProductRecord product) {
        Intent data = new Intent();
        data.putExtra(KEY_PRODUCT_ID, product.productId);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    protected void onDestroy() {
        ItemClickSupport.removeFrom(mRecyclerView);
        super.onDestroy();
    }

    @Override
    public void showProducts(List<FullProductRecord> products) {
        mProductsAdapter.setProducts(products);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        populateAdapter(newText);
        if (newText.length() == 0) {
            mProductsAdapter.removeFilter();
        } else {
            mProductsAdapter.filter(newText);
        }
        return true;
    }

    private void populateAdapter(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "asd"});
        for (int i = 0; i < SUGGESTIONS.length; i++) {
            if (SUGGESTIONS[i].toLowerCase().startsWith(query.toLowerCase()))
                c.addRow(new Object[]{i, SUGGESTIONS[i]});
        }
        mSimpleCursorAdapter.changeCursor(c);
    }
}
