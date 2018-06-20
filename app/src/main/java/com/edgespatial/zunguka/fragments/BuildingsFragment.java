package com.edgespatial.zunguka.fragments;

import android.view.View;

import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.Building;
import com.edgespatial.zunguka.api.viewmodels.BuildingsViewModel;

/**
 * Created by moses on 6/20/18.
 */

public class BuildingsFragment extends PhysicalLocationFragment<BuildingsViewModel, Building> {

    public BuildingsFragment() {
        super(BuildingsViewModel.class);
    }

    @Override
    public void loadResource(boolean loadFromCache) {
        new ZungukaAPI(getContext())
                .getBuildings(this);
    }
}
