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

    public String getCollege() {
        switch (college) {
            case 1:
                return "College of Health Sciences";
            case 2:
                return "College of Engineering and Technology";
            case 3:
                return "College of Pure and Applied Sciences";
            case 4:
                return "College of Human Resources and Development";
            case 5:
                return "Faculty of Agriculture";
            default:
                return "";
        }
    }
}
