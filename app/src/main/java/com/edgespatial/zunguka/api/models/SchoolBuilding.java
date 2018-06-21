package com.edgespatial.zunguka.api.models;

import android.os.Parcel;

import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class SchoolBuilding extends Building {

    public School school;

    public SchoolBuilding(){

    }
    protected SchoolBuilding(Parcel in) {
        super(in);
    }

    public void setBuilding(Building building) {
        this.abbreviation = building.abbreviation;
        this.departments = building.departments;
        this.detail = building.detail;
        this.id = building.id;
        this.image = building.image;
        this.name = building.name;
        this.point = building.point;
    }
}
