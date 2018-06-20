package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

import java.util.ArrayList;

/**
 * Created by moses on 6/20/18.
 */

public class Polygon extends BaseModel {

    @SerializedName("coordinates")
    public ArrayList<ArrayList<Coordinate>> coordinates = new ArrayList<>();
}
