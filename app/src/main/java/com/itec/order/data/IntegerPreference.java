package com.itec.order.data;

import android.content.SharedPreferences;

/**
 * Created by Paul on 5/13/2016.
 */
public class IntegerPreference {
    private final SharedPreferences mPreferences;
    protected String mKey;
    private final int mDefaultValue;

    public IntegerPreference(SharedPreferences preferences, String key) {
        this(preferences, key, 0);
    }

    public IntegerPreference(SharedPreferences preferences, String key, int defaultValue) {
        this.mPreferences = preferences;
        this.mKey = key;
        this.mDefaultValue = defaultValue;
    }

    public int get() {
        return mPreferences.getInt(mKey, mDefaultValue);
    }

    public boolean isSet() {
        return mPreferences.contains(mKey);
    }

    public void set(int value) {
        mPreferences.edit().putInt(mKey, value).apply();
    }

    public void delete() {
        mPreferences.edit().remove(mKey).apply();
    }
}
