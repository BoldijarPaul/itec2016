package com.itec.order.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itec.app.R;
import com.itec.order.contracts.CartPresenter;
import com.itec.order.contracts.CartView;
import com.itec.order.data.persistance.FullProductRecord;
import com.itec.order.ui.activities.ChooseProductActivity;
import com.itec.order.ui.adapters.CartAdapter;
import com.itec.order.ui.app.BaseApp;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements CartView {


    private static final int CODE_CHOOSE = 101;

    @Bind(R.id.cart_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.cart_empty)
    View mEmpty;

    View mSnackView;

    private CartAdapter mCartAdapter;
    private CartPresenter mCartPresenter;


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
        mSnackView = view;
        mCartAdapter = new CartAdapter();
        setupRecycler();
        updateEmptyLayoutVisibility();
        mCartPresenter = new CartPresenter(this);
    }

    private void setupRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCartAdapter);
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                boolean productWasDeleted = mCartAdapter.removeProduct(viewHolder.getAdapterPosition());
                if (productWasDeleted) {
                    updateEmptyLayoutVisibility();
                    Snackbar.make(mSnackView, R.string.product_removed, Snackbar.LENGTH_LONG)
                            .setAction(R.string.undo, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mCartAdapter.undo();
                                }
                            }).show();
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void updateEmptyLayoutVisibility() {
        mEmpty.setVisibility(mCartAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cart_add) {
            startActivityForResult(ChooseProductActivity.createIntent(getContext()), CODE_CHOOSE);
        }
        if (item.getItemId() == R.id.cart_finish) {
            finishOrder();
        }
        return super.onOptionsItemSelected(item);
    }

    private void finishOrder() {
        if (mCartAdapter.getItemCount() == 0) {
            Toast.makeText(getContext(), R.string.no_products, Toast.LENGTH_SHORT).show();
        } else {
            if (BaseApp.getTableId().isSet()) {
                mCartPresenter.sendOrder();
            } else {
                Toast.makeText(getContext(), R.string.pls_scan_table, Toast.LENGTH_SHORT).show();
            }
        }
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
        updateEmptyLayoutVisibility();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void showOrderSuccessfull() {
        Toast.makeText(getContext(), R.string.order_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.unknown_server_error, Toast.LENGTH_SHORT).show();
    }
}
