package com.edgespatial.zunguka.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

/**
 * Created by moses on 6/20/18.
 */

public class Department extends BaseModel implements Parcelable, SearchSuggestion {
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

    public Department(){

    }

    protected Department(Parcel in) {
        id = in.readInt();
        name = in.readString();
        abbreviation = in.readString();
        chairman = in.readString();
        school = in.readInt();
    }

    public static final Creator<Department> CREATOR = new Creator<Department>() {
        @Override
        public Department createFromParcel(Parcel in) {
            return new Department(in);
        }

        @Override
        public Department[] newArray(int size) {
            return new Department[size];
        }
    };

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
        dest.writeString(abbreviation);
        dest.writeString(chairman);
        dest.writeInt(school);
    }
}
