package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class PhysicalLocation extends BaseModel {

    @SerializedName("id")
    public int id;
    @SerializedName(value = "point", alternate = {"geom"})
    public Point point;
    @SerializedName(value = "name", alternate = {"game", "wifi_name"})
    public String name;
    @SerializedName("image")
    public String image;

    public LatLng getLatlng() {
        return point.coordinate.getLatlng();
    }
}
