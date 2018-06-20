package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moses on 6/20/18.
 */

public class Building extends BaseModel {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("abrv")
    public String abbreviation;
    @SerializedName("detail")
    public String detail;
    @SerializedName("image")
    public String image;
    @SerializedName("point")
    public Point point;
    @SerializedName("departments")
    public List<Integer> departments = new ArrayList<>();
}
