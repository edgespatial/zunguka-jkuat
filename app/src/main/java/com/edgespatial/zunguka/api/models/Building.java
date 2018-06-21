package com.edgespatial.zunguka.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.edgespatial.zunguka.R;
import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moses on 6/20/18.
 */

public class Building extends PhysicalLocation {

    @SerializedName("abrv")
    public String abbreviation;
    @SerializedName("detail")
    public String detail;
    @SerializedName("departments")
    public List<Integer> departments = new ArrayList<>();

    public Building() {

    }

    protected Building(Parcel in) {
        super(in);
        abbreviation = in.readString();
        detail = in.readString();
    }

    public static final Creator<Building> CREATOR = new Creator<Building>() {
        @Override
        public Building createFromParcel(Parcel in) {
            return new Building(in);
        }

        @Override
        public Building[] newArray(int size) {
            return new Building[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(abbreviation);
        dest.writeString(detail);
    }

    @Override
    public int getIcon() {
        return R.drawable.buildings;
    }
}
