package com.edgespatial.zunguka.fragments;

import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.School;
import com.edgespatial.zunguka.api.models.SchoolBuilding;
import com.edgespatial.zunguka.api.viewmodels.SchoolsViewModel;
import com.mg.surblime.api.ModelDataListener;

import java.util.List;

/**
 * Created by moses on 6/20/18.
 */

public class SchoolsFragment extends PhysicalLocationFragment<SchoolsViewModel, SchoolBuilding> {
    public SchoolsFragment() {
        super(SchoolsViewModel.class);
    }

    @Override
    public void loadResource(boolean loadFromCache) {
        final ZungukaAPI zungukaAPI = new ZungukaAPI(getContext());
        zungukaAPI.setUseCache(loadFromCache);
        onSuccess(new SchoolsViewModel());
        zungukaAPI.getSchools(new ModelDataListener<List<School>>() {
            @Override
            public void onSuccess(List<School> schools) {
                for (School school : schools) {
                    zungukaAPI.getBuildingFromSchool(school, new ModelDataListener<SchoolBuilding>() {
                        @Override
                        public void onSuccess(SchoolBuilding schoolBuilding) {
                            getResource().addItem(schoolBuilding);
                            notifyItemChanged(getResource().getItems().size() - 1);
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                }
            }

            @Override
            public void onFailure() {

            }
        });
        setSwipeToRefreshEnabled(false);
    }
}
