package com.edgespatial.zunguka.api.viewmodels;

import com.edgespatial.zunguka.BR;
import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.api.models.Building;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 6/20/18.
 */

public class BuildingsViewModel extends RecyclerViewModel<Building> {

    @Override
    public int getVariableId() {
        return BR.building;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_building;
    }

    @Override
    public int getItemListenerId() {
        return BR.listener;
    }

    @Override
    public LayoutManagers.LayoutManagerFactory getLayoutManagerFactory(int orientation) {
        return LayoutManagers.linear();
    }

    @Override
    public String getTitle() {
        return "Buildings";
    }

    @Override
    public String[] filterKeys(Building building) {
        return new String[]{
                building.name,
                building.abbreviation,
                building.detail
        };
    }

}
