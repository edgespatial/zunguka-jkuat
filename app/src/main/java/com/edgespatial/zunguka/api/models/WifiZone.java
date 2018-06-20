package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class WifiZone extends BaseModel {

    @SerializedName("id")
    public int id;
    @SerializedName("location_name")
    public String locationName;
    @SerializedName("wifi_name")
    public String wifiName;
    @SerializedName("speed")
    public String speed;
    @SerializedName("password")
    public String password;
    @SerializedName("geom")
    public Point point;
}
