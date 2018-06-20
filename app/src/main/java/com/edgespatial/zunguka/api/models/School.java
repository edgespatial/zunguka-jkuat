package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class School extends BaseModel {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("abrv")
    public String abbreviation;
    @SerializedName("dean")
    public String dean;
    @SerializedName("college")
    public int college;
}
