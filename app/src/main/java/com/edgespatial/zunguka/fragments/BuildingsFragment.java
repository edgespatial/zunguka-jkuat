package com.edgespatial.zunguka.fragments;

import android.view.View;

import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.Building;
import com.edgespatial.zunguka.api.viewmodels.BuildingsViewModel;
import com.mg.surblime.ui.ResourceCollectionFragment;

/**
 * Created by moses on 6/20/18.
 */

public class BuildingsFragment extends ResourceCollectionFragment<BuildingsViewModel, Building> {

    public BuildingsFragment() {
        super(BuildingsViewModel.class);
    }

    @Override
    public void onItemClick(View view, Building building, int index) {

    }

    @Override
    public void loadResource(boolean loadFromCache) {
        new ZungukaAPI(getContext())
                .getBuildings(this);
    }
}
