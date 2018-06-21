package com.edgespatial.zunguka.fragments;

import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.WifiZone;
import com.edgespatial.zunguka.api.viewmodels.WifiZoneViewModel;

/**
 * Created by moses on 6/21/18.
 */

public class WifiZonesFragment extends PhysicalLocationFragment<WifiZoneViewModel, WifiZone> {

    public WifiZonesFragment() {
        super(WifiZoneViewModel.class);
    }

    @Override
    public void loadResource(boolean loadFromCache) {
        ZungukaAPI zungukaAPI = new ZungukaAPI(getContext());
        zungukaAPI.setUseCache(loadFromCache);

        zungukaAPI.getWifiZones(this);
    }
}
