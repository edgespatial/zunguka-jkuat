package com.edgespatial.zunguka.api.models;

import com.google.gson.annotations.SerializedName;
import com.mg.surblime.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moses on 6/21/18.
 */

public class SearchHistory extends BaseModel {
    @SerializedName("history")
    public List<PhysicalLocation> items = new ArrayList<>();

    public void add(PhysicalLocation physicalLocation) {
        boolean exists = false;
        for (PhysicalLocation physicalLocation1 : items) {
            if (physicalLocation.name.equalsIgnoreCase(physicalLocation1.name)) {
                exists = true;
            }
        }
        if (!exists) {
            items.add(physicalLocation);
        }
    }

    public List<PhysicalLocation> filter(String text) {
        List<PhysicalLocation> items = new ArrayList<>();
        for (PhysicalLocation physicalLocation : this.items) {
            if (physicalLocation.name.toLowerCase().contains(text.toLowerCase())) {
                items.add(physicalLocation);
            }
        }
        return items;
    }
}
