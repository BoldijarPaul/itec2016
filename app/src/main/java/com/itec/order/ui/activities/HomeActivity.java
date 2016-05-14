package com.itec.order.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.itec.app.R;
import com.itec.order.data.service.CachePresenter;
import com.itec.order.ui.fragments.CartFragment;
import com.itec.order.ui.fragments.CategoriesFragment;
import com.itec.order.ui.fragments.NfcFragment;
import com.itec.order.ui.fragments.ScanFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import layout.ProfileFragment;

public class HomeActivity extends BaseActivity {

    private BottomBar mBottomBar;

    private ScanFragment mScanFragment;
    private CartFragment mCartFragment;
    private ProfileFragment mProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new CachePresenter().startCaching();
        setupFragments();
        setupBottomBar(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.home_container);
        if (fragment instanceof NfcFragment) {
            NfcFragment nfcFragment = (NfcFragment) fragment;
            nfcFragment.onNewIntent(intent);
        }

    }

    private void setupFragments() {
        mScanFragment = new ScanFragment();
        mCartFragment = new CartFragment();
        mProfileFragment = new ProfileFragment();
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
                if (menuItemId == R.id.home_profile) {
                    switchFragment(mProfileFragment);
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
