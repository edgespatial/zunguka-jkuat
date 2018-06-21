package com.edgespatial.zunguka.api.viewmodels;

import com.edgespatial.zunguka.BR;
import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.api.models.WifiZone;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 6/20/18.
 */

public class WifiZoneViewModel extends RecyclerViewModel<WifiZone> {
    @Override
    public int getVariableId() {
        return BR.wifiZone;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_wifi_zone;
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
        return "Wifi Zones";
    }

    @Override
    public String[] filterKeys(WifiZone wifiZone) {
        return new String[]{
                wifiZone.locationName,
                wifiZone.name
        };
    }
}
