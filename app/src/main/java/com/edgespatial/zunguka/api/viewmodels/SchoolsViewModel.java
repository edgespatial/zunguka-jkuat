package com.edgespatial.zunguka.api.viewmodels;

import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.BR;
import com.edgespatial.zunguka.api.models.SchoolBuilding;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 6/20/18.
 */

public class SchoolsViewModel extends RecyclerViewModel<SchoolBuilding> {
    @Override
    public int getVariableId() {
        return BR.school_building;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_school_building;
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
        return "Schools";
    }

    @Override
    public String[] filterKeys(SchoolBuilding schoolBuilding) {
        return new String[]{
                schoolBuilding.detail,
                schoolBuilding.school.name,
                schoolBuilding.school.abbreviation,
                schoolBuilding.school.dean,
                schoolBuilding.school.getCollege(),
                schoolBuilding.name,
                schoolBuilding.abbreviation
        };
    }

}
