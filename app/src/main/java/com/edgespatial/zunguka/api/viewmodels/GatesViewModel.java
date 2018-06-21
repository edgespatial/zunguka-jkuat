package com.edgespatial.zunguka.api.viewmodels;

import com.edgespatial.zunguka.BR;
import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.api.models.Gate;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 6/21/18.
 */

public class GatesViewModel extends RecyclerViewModel<Gate> {
    @Override
    public int getVariableId() {
        return BR.gate;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_gate;
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
        return "Gates";
    }

    @Override
    public String[] filterKeys(Gate gate) {
        return new String[]{
                gate.name
        };
    }
}
