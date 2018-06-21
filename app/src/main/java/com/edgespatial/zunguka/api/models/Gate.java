package com.edgespatial.zunguka.api.models;

import android.os.Parcel;

import com.edgespatial.zunguka.R;
import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class Gate extends PhysicalLocation {

    public Gate(){

    }
    protected Gate(Parcel in) {
        super(in);
    }

    public String getChar() {
        return name.charAt(name.length() - 1) + "";
    }

    @Override
    public int getIcon() {
        return R.drawable.gates;
    }
}
