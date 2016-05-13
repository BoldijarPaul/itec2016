package com.itec.order.ui.app;

import android.app.Application;

import com.itec.app.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Paul on 5/12/2016.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
