package com.itec.order.ui.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.itec.app.R;
import com.itec.order.ui.fragments.CartFragment;
import com.itec.order.ui.fragments.ScanFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class HomeActivity extends BaseActivity {

    private BottomBar mBottomBar;

    private ScanFragment mScanFragment;
    private CartFragment mCartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupFragments();
        setupBottomBar(savedInstanceState);
    }

    private void setupFragments() {
        mScanFragment = new ScanFragment();
        mCartFragment = new CartFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.home_container, mScanFragment).commit();
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
    }

    private void setupBottomBar(Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.home_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.home_cart) {
                    switchFragment(mCartFragment);
                }
                if (menuItemId == R.id.home_scan) {
                    switchFragment(mScanFragment);
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }
}
