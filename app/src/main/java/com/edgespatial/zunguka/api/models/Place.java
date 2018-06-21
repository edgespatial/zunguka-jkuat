package com.edgespatial.zunguka.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.edgespatial.zunguka.R;
import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class Place extends PhysicalLocation implements Parcelable {

    @SerializedName("common_name")
    public String commonName;

    public Place(){

    }
    protected Place(Parcel in) {
        super(in);
        commonName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(commonName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    @Override
    public int getIcon() {
        return R.drawable.place;
    }
}
