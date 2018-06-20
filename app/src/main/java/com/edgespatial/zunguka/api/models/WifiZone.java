package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class WifiZone extends PhysicalLocation {

    @SerializedName("location_name")
    public String locationName;
    @SerializedName("speed")
    public String speed;
    @SerializedName("password")
    public String password;
}
