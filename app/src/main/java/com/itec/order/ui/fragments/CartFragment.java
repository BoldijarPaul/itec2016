package com.itec.order.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.itec.app.R;
import com.itec.order.data.persistance.FullProductRecord;
import com.itec.order.ui.activities.ChooseProductActivity;
import com.itec.order.ui.adapters.CartAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    private static final int CODE_CHOOSE = 101;

    @Bind(R.id.cart_recycler)
    RecyclerView mRecyclerView;

    private CartAdapter mCartAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cart, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mCartAdapter = new CartAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCartAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cart_add) {
            startActivityForResult(ChooseProductActivity.createIntent(getContext()), CODE_CHOOSE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_CHOOSE && resultCode == Activity.RESULT_OK &&
                data.getIntExtra(ChooseProductActivity.KEY_PRODUCT_ID, -1) != -1) {
            int productId = data.getIntExtra(ChooseProductActivity.KEY_PRODUCT_ID, -1);
            gotProduct(productId);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void gotProduct(int productId) {
        List<FullProductRecord> products = FullProductRecord.find(FullProductRecord.class, "product_id =?", productId + "");
        if (products.size() > 0) {
            mCartAdapter.addProduct(products.get(0));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

}
