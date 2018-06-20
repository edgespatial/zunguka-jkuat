package com.edgespatial.zunguka.util;

import android.content.Context;

import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.Boundary;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mg.surblime.api.ModelDataListener;

/**
 * Created by moses on 6/20/18.
 */

public class MapTools {

    private static final float MIN_ZOOM = 12f;

    private final MapboxMap mapboxMap;
    private final Context context;

    public MapTools(MapboxMap mapboxMap, Context context) {
        this.mapboxMap = mapboxMap;
        this.context = context;
        mapboxMap.setMinZoomPreference(MIN_ZOOM);
    }

    public MapboxMap getMapboxMap() {
        return mapboxMap;
    }

    public void initialize() {
        updateMapStyle();
        drawBoundary();
    }

    public void updateMapStyle() {
        mapboxMap.setStyle(Settings.isSatelliteMapStyle(context) ? Style.SATELLITE_STREETS : Style.MAPBOX_STREETS);
    }

    private void drawBoundary() {
        new ZungukaAPI(context)
                .getBoundary(new ModelDataListener<Boundary>() {
                    @Override
                    public void onSuccess(Boundary boundary) {
                        boundary.draw(mapboxMap, context);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
    }
}
