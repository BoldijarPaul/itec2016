package com.itec.order.ui.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.itec.app.R;
import com.itec.order.data.IntegerPreference;
import com.itec.order.data.StringPreference;
import com.orm.SugarApp;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Paul on 5/12/2016.
 */
public class BaseApp extends SugarApp {

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

    private static IntegerPreference tableId;

    public static IntegerPreference getTableId() {
        if (tableId == null) {
            tableId = new IntegerPreference(mSharedPreferences, "tableId");
        }
        return tableId;
    }

    private static StringPreference note;

    public static StringPreference getNote() {
        if (note == null) {
            note = new StringPreference(mSharedPreferences, "note");
        }
        return note;
    }

    private static StringPreference email;

    public static StringPreference getEmail() {
        if (email == null) {
            email = new StringPreference(mSharedPreferences, "email");
        }
        return email;
    }

}
