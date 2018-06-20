package com.edgespatial.zunguka.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by moses on 6/20/18.
 */

public class Settings {

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferences.Editor editShardPreferences(Context context) {
        return getSharedPreferences(context).edit();
    }
}
