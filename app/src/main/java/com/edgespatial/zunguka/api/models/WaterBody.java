package com.edgespatial.zunguka.api.models;

import android.os.Parcel;

import com.edgespatial.zunguka.R;
import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class WaterBody extends PhysicalLocation {
    public WaterBody(){

    }
    protected WaterBody(Parcel in) {
        super(in);
    }

    @Override
    public int getIcon() {
        return R.drawable.water_body;
    }
}
