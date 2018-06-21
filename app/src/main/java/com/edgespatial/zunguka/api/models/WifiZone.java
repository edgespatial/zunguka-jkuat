package com.edgespatial.zunguka.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.edgespatial.zunguka.R;
import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class WifiZone extends PhysicalLocation implements Parcelable {

    @SerializedName("location_name")
    public String locationName;
    @SerializedName("speed")
    public String speed;
    @SerializedName("password")
    public String password;

    public WifiZone() {

    }

    protected WifiZone(Parcel in) {
        super(in);
        locationName = in.readString();
        speed = in.readString();
        password = in.readString();
    }

    public static final Creator<WifiZone> CREATOR = new Creator<WifiZone>() {
        @Override
        public WifiZone createFromParcel(Parcel in) {
            return new WifiZone(in);
        }

        @Override
        public WifiZone[] newArray(int size) {
            return new WifiZone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(locationName);
        dest.writeString(speed);
        dest.writeString(password);
    }

    @Override
    public int getIcon() {
        return R.drawable.wifi_zones;
    }
}
