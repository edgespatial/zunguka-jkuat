package com.edgespatial.zunguka.api.models;

import com.mg.surblime.BaseModel;

import java.util.ArrayList;

/**
 * Created by moses on 6/21/18.
 */

public class SearchResults extends BaseModel {

    private final ArrayList<PhysicalLocation> results;


    public SearchResults(Object[] results, String text) {
        this.results = new ArrayList<>();
        for (Object object : results) {
            ArrayList<PhysicalLocation> list = (ArrayList<PhysicalLocation>) object;
            for (PhysicalLocation physicalLocation : list) {
                if (physicalLocation.name.toLowerCase().contains(text.toLowerCase())) {
                    this.results.add(physicalLocation);
                }
            }
        }
    }

    public ArrayList<Building> getBuildings() {
        return get(Building.class);
    }

    public ArrayList<Place> getPlaces() {
        return get(Place.class);
    }

    public ArrayList<WifiZone> getWifiZones() {
        return get(WifiZone.class);
    }

    public ArrayList<Park> getParks() {
        return get(Park.class);
    }

    public ArrayList<Gate> getGates() {
        return get(Gate.class);
    }

    public ArrayList<Field> getFields() {
        return get(Field.class);
    }

    public ArrayList<WaterBody> getWaterBodies() {
        return get(WaterBody.class);
    }

    private <T extends PhysicalLocation> ArrayList<T> get(Class<T> classOfT) {
        ArrayList<T> result = new ArrayList<>();
        for (PhysicalLocation physicalLocation : results) {
            if (physicalLocation.getClass() == classOfT) {
                result.add((T) physicalLocation);
            }
        }
        return result;
    }

    public ArrayList<PhysicalLocation> getResults() {
        return new ArrayList<>(results.subList(0, 10));
    }
}
