package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;

/**
 * Created by moses on 6/20/18.
 */

public class MultiPolygon {

    @SerializedName("coordinates")
    public ArrayList<ArrayList<ArrayList<Coordinate>>> coordinates = new ArrayList<>();
}