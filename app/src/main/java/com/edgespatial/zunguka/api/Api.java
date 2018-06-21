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

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by moses on 6/20/18.
 */

public interface Api {
    @GET("boundary/")
    Call<List<Boundary>> getBoundary();

    @GET("schools/")
    Call<List<School>> getSchools();

    @GET("school_buildings/{school_id}")
    Call<Building> getSchoolBuilding(@Path("school_id") int schoolId);

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

    @GET("buildings/")
    Observable<List<Building>> searchBuildings();

    @GET("places/")
    Observable<List<Place>> searchPlaces();

    @GET("wifizones/")
    Observable<List<WifiZone>> searchWifiZones();

    @GET("parks/")
    Observable<List<Park>> searchParks();

    @GET("fields/")
    Observable<List<Field>> searchFields();

    @GET("waterbodies/")
    Observable<List<WaterBody>> searchWaterBodies();

    @GET("gates/")
    Observable<List<Gate>> searchGates();
}
