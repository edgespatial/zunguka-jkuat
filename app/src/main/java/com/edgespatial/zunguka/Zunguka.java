package com.edgespatial.zunguka;

import android.app.Application;

import com.mapbox.mapboxsdk.Mapbox;

/**
 * Created by moses on 6/20/18.
 */

public class Zunguka extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Mapbox.getInstance(this, getString(R.string.map_box_access_token));
    }
}
