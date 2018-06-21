package com.edgespatial.zunguka.api.viewmodels;

import com.edgespatial.zunguka.R;
import com.mg.surblime.BR;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 6/21/18.
 */

public class SearchResultsViewModel extends RecyclerViewModel<PlaceSuggestionViewModel> {
    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_place_suggestion;
    }

    @Override
    public int getItemListenerId() {
        return 0;
    }

    @Override
    public LayoutManagers.LayoutManagerFactory getLayoutManagerFactory(int orientation) {
        return LayoutManagers.linear();
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getLayoutResource() {
        return com.mg.surblime.R.layout.fragment_resource_collection;
    }
}
