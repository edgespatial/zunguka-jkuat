package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mg.surblime.BaseModel;

import java.util.ArrayList;

/**
 * Created by moses on 6/20/18.
 */

public class Polygon extends BaseModel {

    @SerializedName("coordinates")
    public ArrayList<ArrayList<Coordinate>> coordinates = new ArrayList<>();


    public ArrayList<LatLng> getLatlng() {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        for (Coordinate coordinate : coordinates.get(0)) {
            arrayList.add(coordinate.getLatlng());
        }
        return arrayList;
    }
}
