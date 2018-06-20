package com.edgespatial.zunguka.api;

import android.content.Context;

import com.edgespatial.zunguka.api.models.Boundary;
import com.edgespatial.zunguka.api.models.Building;
import com.edgespatial.zunguka.api.viewmodels.BuildingsViewModel;
import com.google.gson.GsonBuilder;
import com.mg.surblime.api.ModelDataListener;
import com.mg.surblime.api.SimpleQuery;
import com.mg.surblime.api.SurblimeAPI;
import com.mg.surblime.api.ViewModelDataListener;

import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by moses on 6/20/18.
 */

public class ZungukaAPI extends SurblimeAPI {

    private final static String BASE_URL = "http://zunguka-api.herokuapp.com/api/v1/";

    public ZungukaAPI(Context context) {
        super(context);
    }

    @Override
    public Interceptor getRetrofitInterceptor() {
        return null;
    }

    @Override
    public GsonBuilder createGsonBuilder(GsonBuilder gsonBuilder) {
        return gsonBuilder;
    }

    @Override
    public String getBaseURL() {
        return BASE_URL;
    }

    public void getBoundary(final ModelDataListener<Boundary> dataListener) {
        new SimpleQuery<List<Boundary>>()
                .with(this)
                .callback(new ModelDataListener<List<Boundary>>() {
                    @Override
                    public void onSuccess(List<Boundary> boundaries) {
                        dataListener.onSuccess(boundaries.get(0));
                    }

                    @Override
                    public void onFailure() {
                        dataListener.onFailure();
                    }
                }).invoke("getBoundary")
                .execute();
    }

    public void getBuildings(final ViewModelDataListener<BuildingsViewModel, Building> viewModelBuildingViewModelDataListener) {
        new SimpleQuery<List<Building>>()
                .with(this)
                .callback(new ModelDataListener<List<Building>>() {
                    @Override
                    public void onSuccess(List<Building> buildings) {
                        BuildingsViewModel buildingsViewModel = new BuildingsViewModel();
                        buildingsViewModel.setItems(buildings);
                        viewModelBuildingViewModelDataListener.onSuccess(buildingsViewModel);
                    }

                    @Override
                    public void onFailure() {
                        viewModelBuildingViewModelDataListener.onFailure();
                    }
                }).invoke("getBuildings")
                .execute();
    }

    @Override
    public Class<?> getRetrofitInterface() {
        return Api.class;
    }
}
