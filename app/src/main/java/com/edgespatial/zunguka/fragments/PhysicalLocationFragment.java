package com.edgespatial.zunguka.fragments;

import android.view.View;

import com.edgespatial.zunguka.activities.MainActivity;
import com.edgespatial.zunguka.api.models.PhysicalLocation;
import com.mg.surblime.ObservableRecyclerViewModel;
import com.mg.surblime.ui.ResourceCollectionFragment;

/**
 * Created by moses on 6/20/18.
 */

public abstract class PhysicalLocationFragment<T extends ObservableRecyclerViewModel<S>, S extends PhysicalLocation> extends ResourceCollectionFragment<T, S> {
    public PhysicalLocationFragment(Class<T> viewModelClass) {
        super(viewModelClass);
    }

    @Override
    public void onItemClick(View view, S s, int index) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.showPositionMarker(s);
        }
    }
}
