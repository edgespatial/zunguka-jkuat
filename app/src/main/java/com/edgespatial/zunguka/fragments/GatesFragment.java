package com.edgespatial.zunguka.fragments;

import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.Gate;
import com.edgespatial.zunguka.api.viewmodels.GatesViewModel;

/**
 * Created by moses on 6/21/18.
 */

public class GatesFragment extends PhysicalLocationFragment<GatesViewModel, Gate> {

    public GatesFragment() {
        super(GatesViewModel.class);
    }

    @Override
    public void loadResource(boolean loadFromCache) {
        ZungukaAPI zungukaAPI = new ZungukaAPI(getContext());
        zungukaAPI.setUseCache(loadFromCache);
        zungukaAPI.getGates(this);
    }
}
