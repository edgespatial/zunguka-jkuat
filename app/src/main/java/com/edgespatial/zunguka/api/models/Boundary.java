package com.edgespatial.zunguka.api.models;

import android.content.Context;

import com.edgespatial.zunguka.R;
import com.google.gson.annotations.SerializedName;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class Boundary extends BaseModel {
    @SerializedName("name")
    public String name;

    @SerializedName("geom")
    public Polygon polygon;

    public void draw(MapboxMap map, Context context) {
        PolylineOptions boundsArea = new PolylineOptions();
        boundsArea.addAll(polygon.getLatlng());

        boundsArea.width(2f);
        LatLngBounds latLngBounds = new LatLngBounds.Builder().includes(polygon.getLatlng()).build();
        map.setLatLngBoundsForCameraTarget(latLngBounds);

        boundsArea.color(context.getResources().getColor(R.color.colorAccent));

        map.addPolyline(boundsArea);
    }
}
