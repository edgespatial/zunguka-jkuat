package com.edgespatial.zunguka.util;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;

import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.Boundary;
import com.edgespatial.zunguka.api.models.PhysicalLocation;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdate;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mg.surblime.api.ModelDataListener;


/**
 * Created by moses on 6/20/18.
 */

public class MapTools {

    private static final float MIN_ZOOM = 12f;
    private static final float DEFAULT_ZOOM = 14f;

    private final MapboxMap mapboxMap;
    private final Context context;
    private final MapView mapView;

    private Marker positionMarker;

    public MapTools(MapboxMap mapboxMap, Context context, MapView mapView) {
        this.mapboxMap = mapboxMap;
        this.context = context;
        this.mapView = mapView;
        mapboxMap.setMinZoomPreference(MIN_ZOOM);
        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Settings.getInitialPosition(context)), DEFAULT_ZOOM));
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

    public void displaySingleLocation(PhysicalLocation physicalLocation) {
        if (positionMarker != null) {
            mapboxMap.removeMarker(positionMarker);
        }
        MarkerOptions markerOptions = new MarkerOptions()
                .position(physicalLocation.getLatlng())
                .title(physicalLocation.name)
                .icon(IconFactory.getInstance(context).fromResource(R.drawable.location_map_pin));
        mapboxMap.addMarker(markerOptions);
        positionMarker = markerOptions.getMarker();
        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(physicalLocation.getLatlng(), 16f));
    }

    private static class LatLngEvaluator implements TypeEvaluator<LatLng> {
        // Method is used to interpolate the marker animation.

        private LatLng latLng = new LatLng();

        @Override
        public LatLng evaluate(float fraction, LatLng startValue, LatLng endValue) {
            latLng.setLatitude(startValue.getLatitude()
                    + ((endValue.getLatitude() - startValue.getLatitude()) * fraction));
            latLng.setLongitude(startValue.getLongitude()
                    + ((endValue.getLongitude() - startValue.getLongitude()) * fraction));
            return latLng;
        }
    }
}
