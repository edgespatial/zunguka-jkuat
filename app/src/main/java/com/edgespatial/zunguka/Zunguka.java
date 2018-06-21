package com.edgespatial.zunguka;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation;

/**
 * Created by moses on 6/20/18.
 */

public class Zunguka extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Mapbox.getInstance(this, getString(R.string.map_box_access_token));
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        MapboxNavigation navigation = new MapboxNavigation(this, getString(R.string.map_box_access_token));
        LocationEngine locationEngine = new LocationEngineProvider(this).obtainBestLocationEngineAvailable();
        navigation.setLocationEngine(locationEngine);

    }
}
