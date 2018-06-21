package com.edgespatial.zunguka.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.viewmodels.PlaceSuggestionViewModel;
import com.edgespatial.zunguka.api.viewmodels.SearchResultsViewModel;
import com.mg.surblime.ui.ResourceCollectionFragment;

/**
 * Created by moses on 6/21/18.
 */

public class DirectionSuggestionFragment extends ResourceCollectionFragment<SearchResultsViewModel, PlaceSuggestionViewModel> implements TextWatcher {

    private String searchText = "";

    public DirectionSuggestionFragment() {
        super(SearchResultsViewModel.class);
    }

    @Override
    public void onItemClick(View view, PlaceSuggestionViewModel placeSuggestionViewModel, int index) {

    }

    @Override
    public void loadResource(boolean loadFromCache) {
        ZungukaAPI zungukaAPI = new ZungukaAPI(getContext());
        zungukaAPI.setUseCache(true);
        zungukaAPI.search(searchText, this);
        setSwipeToRefreshEnabled(false);
    }

    @Override
    public void onSuccess(SearchResultsViewModel searchResultsViewModel) {
        super.onSuccess(searchResultsViewModel);
        notifyDataSetChanged();
    }

    @Override
    public void onTextChanged(String text) {
        this.searchText = text;
        this.onRefresh();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        onTextChanged(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
