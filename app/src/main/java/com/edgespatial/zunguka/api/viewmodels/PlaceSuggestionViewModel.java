package com.edgespatial.zunguka.api.viewmodels;

import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.api.models.PhysicalLocation;
import com.mg.surblime.BR;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 6/21/18.
 */

public class PlaceSuggestionViewModel<T extends PhysicalLocation> extends RecyclerViewModel<T> {

    public String title;

    public PlaceSuggestionViewModel(List<T> items, String title) {
        this.setItems(items);
        this.title = title;
    }

    public PlaceSuggestionViewModel() {

    }

    @Override
    public int getVariableId() {
        return BR.suggestion;
    }

    @Override
    public int getItemLayoutResource() {
        return R.layout.item_suggestion;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_place_suggestion;
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
        return title;
    }
}
