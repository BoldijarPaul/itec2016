package com.itec.order.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itec.app.R;
import com.itec.order.ui.activities.ChooseProductActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    private static final int CODE_CHOOSE = 101;


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
            Toast.makeText(getContext(), "Am primit product id", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

}
