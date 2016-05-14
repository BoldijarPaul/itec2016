package com.itec.order.data;

import android.content.SharedPreferences;

/**
 * Created by Paul on 5/13/2016.
 */
public class StringPreference {
    private final SharedPreferences mPreferences;
    protected String mKey;
    private final String mDefaultValue;

    public StringPreference(SharedPreferences preferences, String key) {
        this(preferences, key, null);
    }

    public StringPreference(SharedPreferences preferences, String key, String defaultValue) {
        this.mPreferences = preferences;
        this.mKey = key;
        this.mDefaultValue = defaultValue;
    }

    public String get() {
        return mPreferences.getString(mKey, mDefaultValue);
    }

    public boolean isSet() {
        return mPreferences.contains(mKey);
    }

    public void set(String value) {
        mPreferences.edit().putString(mKey, value).apply();
    }

    public void delete() {
        mPreferences.edit().remove(mKey).apply();
    }
}
