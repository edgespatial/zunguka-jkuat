package com.edgespatial.zunguka.api;

import android.content.Context;

import com.edgespatial.zunguka.Zunguka;
import com.edgespatial.zunguka.api.models.Boundary;
import com.edgespatial.zunguka.api.models.Building;
import com.edgespatial.zunguka.api.models.Gate;
import com.edgespatial.zunguka.api.models.PhysicalLocation;
import com.edgespatial.zunguka.api.models.Point;
import com.edgespatial.zunguka.api.models.School;
import com.edgespatial.zunguka.api.models.SchoolBuilding;
import com.edgespatial.zunguka.api.models.SearchHistory;
import com.edgespatial.zunguka.api.models.SearchResults;
import com.edgespatial.zunguka.api.models.WaterBody;
import com.edgespatial.zunguka.api.models.WifiZone;
import com.edgespatial.zunguka.api.viewmodels.BuildingsViewModel;
import com.edgespatial.zunguka.api.viewmodels.GatesViewModel;
import com.edgespatial.zunguka.api.viewmodels.PlaceSuggestionViewModel;
import com.edgespatial.zunguka.api.viewmodels.SchoolsViewModel;
import com.edgespatial.zunguka.api.viewmodels.SearchResultsViewModel;
import com.edgespatial.zunguka.api.viewmodels.WifiZoneViewModel;
import com.edgespatial.zunguka.util.ZungukaCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mg.surblime.api.ModelDataListener;
import com.mg.surblime.api.SimpleQuery;
import com.mg.surblime.api.SurblimeAPI;
import com.mg.surblime.api.SurblimeAPIQuery;
import com.mg.surblime.api.ViewModelDataListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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
        gsonBuilder.registerTypeAdapter(Building.class, new BuildingDeserializer());
        gsonBuilder.registerTypeAdapter(WaterBody.class, new WaterBodyDeserializer());
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

    public void getWifiZones(final ViewModelDataListener<WifiZoneViewModel, WifiZone> viewModelWifiZoneViewModelDataListener) {
        new SimpleQuery<List<WifiZone>>()
                .with(this)
                .callback(new ModelDataListener<List<WifiZone>>() {
                    @Override
                    public void onSuccess(List<WifiZone> wifiZones) {
                        WifiZoneViewModel wifiZoneViewModel = new WifiZoneViewModel();
                        wifiZoneViewModel.setItems(wifiZones);
                        viewModelWifiZoneViewModelDataListener.onSuccess(wifiZoneViewModel);
                    }

                    @Override
                    public void onFailure() {
                        viewModelWifiZoneViewModelDataListener.onFailure();
                    }
                })
                .invoke("getWifiZones")
                .execute();
    }

    public void getGates(final ViewModelDataListener<GatesViewModel, Gate> viewModelGateViewModelDataListener) {
        new SimpleQuery<List<Gate>>()
                .with(this)
                .callback(new ModelDataListener<List<Gate>>() {
                    @Override
                    public void onSuccess(List<Gate> gates) {
                        GatesViewModel gatesViewModel = new GatesViewModel();
                        gatesViewModel.setItems(gates);
                        viewModelGateViewModelDataListener.onSuccess(gatesViewModel);
                    }

                    @Override
                    public void onFailure() {
                        viewModelGateViewModelDataListener.onFailure();
                    }
                }).invoke("getGates")
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

    public void getBuildingFromSchool(final School school, final ModelDataListener<SchoolBuilding> modelDataListener){
        new SimpleQuery<Building>()
                .with(ZungukaAPI.this)
                .invoke("getSchoolBuilding", int.class)
                .args(school.id)
                .callback(new ModelDataListener<Building>() {
                    @Override
                    public void onSuccess(Building building) {
                        SchoolBuilding schoolBuilding = new SchoolBuilding();
                        schoolBuilding.school = school;
                        schoolBuilding.setBuilding(building);
                        modelDataListener.onSuccess(schoolBuilding);
                    }

                    @Override
                    public void onFailure() {
                        modelDataListener.onFailure();
                    }
                }).execute();
    }
    public void getSchools(final ModelDataListener<List<School>> modelDataListener) {
        new SimpleQuery<List<School>>()
                .with(this)
                .invoke("getSchools")
                .callback(modelDataListener).execute();
    }

    public void search(final String text, final ViewModelDataListener<SearchResultsViewModel, PlaceSuggestionViewModel> viewModelPlaceSuggestionViewModelViewModelDataListener) {
        if (text == null || text.isEmpty()) {
            SearchResultsViewModel searchResultsViewModel = new SearchResultsViewModel();
            SearchHistory searchHistory = new ZungukaCache().with(getContext())
                    .get(SearchHistory.class);
            if (searchHistory == null) {
                searchHistory = new SearchHistory();
            }
            searchResultsViewModel.addItem(new PlaceSuggestionViewModel(searchHistory.items, "History"));
            viewModelPlaceSuggestionViewModelViewModelDataListener.onSuccess(searchResultsViewModel);
        } else {
            new SimpleQuery<SearchResults>()
                    .with(this)
                    .subscribe("searchBuildings", null)
                    .subscribe("searchPlaces", null)
                    .subscribe("searchWifiZones", null)
                    .subscribe("searchParks", null)
                    .subscribe("searchGates", null)
                    .subscribe("searchFields", null)
                    .subscribe("searchWaterBodies", null)
                    .callback(new ModelDataListener<SearchResults>() {
                        @Override
                        public void onSuccess(SearchResults searchResults) {
                            SearchResultsViewModel searchResultsViewModel = new SearchResultsViewModel();
                            SearchHistory searchHistory = new ZungukaCache().with(getContext())
                                    .get(SearchHistory.class);
                            if (searchHistory == null) {
                                searchHistory = new SearchHistory();
                            }
                            searchResultsViewModel.addItems(Arrays.asList(
                                    new PlaceSuggestionViewModel(searchHistory.filter(text), "History"),
                                    new PlaceSuggestionViewModel(searchResults.getBuildings(), "Buildings"),
                                    new PlaceSuggestionViewModel(searchResults.getFields(), "Fields"),
                                    new PlaceSuggestionViewModel(searchResults.getGates(), "Gates"),
                                    new PlaceSuggestionViewModel(searchResults.getParks(), "Parks"),
                                    new PlaceSuggestionViewModel(searchResults.getPlaces(), "Places"),
                                    new PlaceSuggestionViewModel(searchResults.getWaterBodies(), "Water Bodies"),
                                    new PlaceSuggestionViewModel(searchResults.getWifiZones(), "Wifi Zones")
                            ));

                            viewModelPlaceSuggestionViewModelViewModelDataListener.onSuccess(searchResultsViewModel);
                        }

                        @Override
                        public void onFailure() {
                            viewModelPlaceSuggestionViewModelViewModelDataListener.onFailure();
                        }
                    })
                    .zip(new SurblimeAPIQuery.Zipper<SearchResults>() {
                        @Override
                        public SearchResults combine(Object[] objects) {
                            return new SearchResults(objects, text);
                        }
                    });
        }
    }

    public void search(final String text, ModelDataListener<SearchResults> modelDataListener) {
        new SimpleQuery<SearchResults>()
                .with(this)
                .subscribe("searchBuildings", null)
                .subscribe("searchPlaces", null)
                .subscribe("searchWifiZones", null)
                .subscribe("searchParks", null)
                .subscribe("searchGates", null)
                .subscribe("searchFields", null)
                .subscribe("searchWaterBodies", null)
                .callback(modelDataListener)
                .zip(new SurblimeAPIQuery.Zipper<SearchResults>() {
                    @Override
                    public SearchResults combine(Object[] objects) {
                        return new SearchResults(objects, text);
                    }
                });
    }

    @Override
    public Class<?> getRetrofitInterface() {
        return Api.class;
    }

    static class BuildingSerializer implements JsonSerializer<Building> {

        @Override
        public JsonElement serialize(Building src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("id", new JsonPrimitive(src.id));
            jsonObject.add("name", new JsonPrimitive(src.name));
            jsonObject.add("abrv", new JsonPrimitive(src.abbreviation));
            jsonObject.add("image", new JsonPrimitive(src.image));
            jsonObject.add("point", new Gson().toJsonTree(src.point));
            jsonObject.add("departments", new Gson().toJsonTree(src.departments));
            return jsonObject;
        }
    }

    static class BuildingDeserializer implements JsonDeserializer<Building> {

        @Override
        public Building deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            Building building = new Building();
            com.google.gson.JsonObject jsonObject = json.getAsJsonObject();


            building.id = jsonObject.get("id").getAsInt();
            building.name = jsonObject.get("name") instanceof JsonNull ? "" : jsonObject.get("name").getAsString();
            building.abbreviation = jsonObject.get("abrv") instanceof JsonNull ? "" : jsonObject.get("abrv").getAsString();
            building.image = jsonObject.get("image") instanceof JsonNull ? "" : jsonObject.get("image").getAsString();
            building.point = new Gson().fromJson(jsonObject.get("point"), Point.class);
            building.departments = new ArrayList<>();
            JsonArray jsonElements = jsonObject.getAsJsonArray("departments");
            for (int i = 0; i < jsonElements.size(); i++) {
                building.departments.add(jsonElements.get(i).getAsInt());
            }

            return building;
        }

    }

    static class WaterBodyDeserializer implements JsonDeserializer<WaterBody> {

        @Override
        public WaterBody deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            WaterBody waterbody = new WaterBody();
            com.google.gson.JsonObject jsonObject = json.getAsJsonObject();


            waterbody.id = jsonObject.get("id").getAsInt();
            waterbody.name = jsonObject.get("name") instanceof JsonNull ? "" : jsonObject.get("name").getAsString();
            waterbody.image = jsonObject.get("image") instanceof JsonNull ? "" : jsonObject.get("image").getAsString();
            waterbody.point = new Gson().fromJson(jsonObject.get("point"), Point.class);

            return waterbody;
        }
    }
}
