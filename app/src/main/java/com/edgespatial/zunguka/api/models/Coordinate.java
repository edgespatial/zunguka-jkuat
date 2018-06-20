package com.edgespatial.zunguka.api.models;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mg.surblime.BaseModel;

import java.util.ArrayList;

/**
 * Created by moses on 6/20/18.
 */

public class Coordinate extends ArrayList<Double> {

    public double getLatitude() {
        return get(1);
    }

    public double getLongitude() {
        return get(0);
    }

    public LatLng getLatlng() {
        return new LatLng(getLatitude(), getLongitude());
    }
}
