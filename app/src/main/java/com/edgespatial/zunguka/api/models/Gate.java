package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class Gate extends BaseModel {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("image")
    public String image;
    @SerializedName("geom")
    public Point point;
}
