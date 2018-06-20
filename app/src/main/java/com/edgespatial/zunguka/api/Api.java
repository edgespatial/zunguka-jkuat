package com.edgespatial.zunguka.api;

import com.edgespatial.zunguka.api.models.Boundary;
import com.edgespatial.zunguka.api.models.Building;
import com.edgespatial.zunguka.api.models.Department;
import com.edgespatial.zunguka.api.models.Field;
import com.edgespatial.zunguka.api.models.Gate;
import com.edgespatial.zunguka.api.models.Park;
import com.edgespatial.zunguka.api.models.Place;
import com.edgespatial.zunguka.api.models.School;
import com.edgespatial.zunguka.api.models.WaterBody;
import com.edgespatial.zunguka.api.models.WifiZone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by moses on 6/20/18.
 */

public interface Api {
    @GET("boundary/")
    Call<List<Boundary>> getBoundary();

    @GET("schools/")
    Call<List<School>> getSchools();

    @GET("departments/")
    Call<List<Department>> getDepartments();

    @GET("buildings/")
    Call<List<Building>> getBuildings();

    @GET("places/")
    Call<List<Place>> getPlaces();

    @GET("wifizones/")
    Call<List<WifiZone>> getWifiZones();

    @GET("parks/")
    Call<List<Park>> getParks();

    @GET("fields/")
    Call<List<Field>> getFields();

    @GET("waterbodies/")
    Call<List<WaterBody>> getWaterBodies();

    @GET("gates/")
    Call<List<Gate>> getGates();
}
