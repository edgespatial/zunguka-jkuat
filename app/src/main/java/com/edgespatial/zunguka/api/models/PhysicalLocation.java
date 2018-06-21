package com.edgespatial.zunguka.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.edgespatial.zunguka.R;
import com.google.gson.annotations.SerializedName;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class PhysicalLocation extends BaseModel implements SearchSuggestion, Parcelable {

    @SerializedName("id")
    public int id;
    @SerializedName(value = "point", alternate = {"geom"})
    public Point point;
    @SerializedName(value = "name", alternate = {"game", "wifi_name"})
    public String name;
    @SerializedName("image")
    public String image;

    public PhysicalLocation() {

    }

    protected PhysicalLocation(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<PhysicalLocation> CREATOR = new Creator<PhysicalLocation>() {
        @Override
        public PhysicalLocation createFromParcel(Parcel in) {
            return new PhysicalLocation(in);
        }

        @Override
        public PhysicalLocation[] newArray(int size) {
            return new PhysicalLocation[size];
        }
    };

    public LatLng getLatlng() {
        return point.coordinate.getLatlng();
    }

    @Override
    public String getBody() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(image);
    }

    public @DrawableRes
    int getIcon() {
        return R.drawable.history;
    }

    public boolean isEqual(PhysicalLocation other){
        return other.getLatlng().equals(getLatlng());
    }
}
