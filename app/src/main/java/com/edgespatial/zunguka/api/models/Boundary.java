package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class Boundary extends BaseModel {
    @SerializedName("name")
    public String name;

    @SerializedName("geom")
    public Polygon polygon;
}
