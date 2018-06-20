package com.edgespatial.zunguka.fragments;

import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.SchoolBuilding;
import com.edgespatial.zunguka.api.viewmodels.SchoolsViewModel;

/**
 * Created by moses on 6/20/18.
 */

public class SchoolsFragment extends PhysicalLocationFragment<SchoolsViewModel,SchoolBuilding> {
    public SchoolsFragment() {
        super(SchoolsViewModel.class);
    }

    @Override
    public void loadResource(boolean loadFromCache) {
        ZungukaAPI zungukaAPI = new ZungukaAPI(getContext());
        zungukaAPI.setUseCache(loadFromCache);
        zungukaAPI.getSchools(this);
        setSwipeToRefreshEnabled(false);
    }
}
