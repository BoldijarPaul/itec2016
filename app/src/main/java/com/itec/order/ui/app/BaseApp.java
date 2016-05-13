package com.itec.order.ui.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.itec.app.R;
import com.itec.order.data.IntegerPreference;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Paul on 5/12/2016.
 */
public class BaseApp extends Application {

    private static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        mSharedPreferences = getSharedPreferences("app_name", Context.MODE_PRIVATE);
    }

    private static IntegerPreference token;

    public static IntegerPreference getToken() {
        if (token == null) {
            token = new IntegerPreference(mSharedPreferences, "token");
        }
        return token;
    }

}
