package com.itec.order.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.LoginFilter;

import com.itec.app.R;
import com.itec.order.ui.fragments.CategoriesFragment;
import com.itec.order.ui.fragments.LoginFragment;
import com.itec.order.ui.fragments.TutorialFragment;
import com.itec.order.ui.fragments.WelcomeFragment;

/**
 * Created by bjz on 5/13/2016.
 */
public class WelcomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragments;

    public WelcomeFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mFragments = new Fragment[3];
        mFragments[0] = new WelcomeFragment();
        mFragments[1] = TutorialFragment.newInstance(R.drawable.tutorial_swipe, context.getString(R.string.tutorial_swipe));
        mFragments[2] = new LoginFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
