package com.edgespatial.zunguka.util;

import android.content.Context;

import com.edgespatial.zunguka.R;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mg.surblime.util.Tools;

/**
 * Created by moses on 6/20/18.
 */

public class Settings {
    private static final String PREFERENCE_SATTELITE_MAP_STYLE = "satellite_map_style";

    public static void setSatelliteMapStyle(Context context, boolean satellite) {
        Tools.getSharedPreferences(context)
                .edit()
                .putBoolean(PREFERENCE_SATTELITE_MAP_STYLE, satellite)
                .apply();
    }


    public static LatLng getInitialPosition(Context context) {
        return new LatLng(Double.parseDouble(context.getString(R.string.location_latitude)), Double.parseDouble(context.getString(R.string.location_longitude)));
    }

    public static boolean isSatelliteMapStyle(Context context) {
        return Tools.getSharedPreferences(context)
                .getBoolean(PREFERENCE_SATTELITE_MAP_STYLE, false);
    }
}
