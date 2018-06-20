package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class Department extends BaseModel {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("abrv")
    public String abbreviation;
    @SerializedName("chairman")
    public String chairman;
    @SerializedName("school")
    public int school;
}
