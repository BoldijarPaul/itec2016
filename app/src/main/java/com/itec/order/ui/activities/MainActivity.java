package com.itec.order.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.itec.app.R;
import com.itec.order.ui.adapters.WelcomeFragmentPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends BaseActivity {
    @Bind(R.id.main_view_pager)
    ViewPager mViewPager;
    @Bind(R.id.main_indicator)
    CircleIndicator mIndicator;
    private WelcomeFragmentPagerAdapter mWelcomeFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWelcomeFragmentPagerAdapter = new WelcomeFragmentPagerAdapter(getSupportFragmentManager());
        ButterKnife.bind(this);
        mViewPager.setAdapter(mWelcomeFragmentPagerAdapter);
        mIndicator.setViewPager(mViewPager);

    }
}
