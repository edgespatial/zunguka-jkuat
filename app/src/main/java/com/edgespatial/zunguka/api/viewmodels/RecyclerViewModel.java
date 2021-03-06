package com.edgespatial.zunguka.api.viewmodels;

import android.databinding.Bindable;
import android.widget.EditText;

import com.edgespatial.zunguka.BR;
import com.edgespatial.zunguka.R;
import com.mg.surblime.BaseModel;
import com.mg.surblime.ObservableRecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.LayoutManagers;

/**
 * Created by moses on 6/20/18.
 */

public abstract class RecyclerViewModel<T extends BaseModel> extends ObservableRecyclerViewModel<T> {

    public List<T> list = new ArrayList<>();

    @Override
    @Bindable
    public List<T> getItems() {
        return list;
    }

    @Override
    public void addItems(List<T> items) {
        list.addAll(items);
    }

    @Override
    public void setItems(List<T> items) {
        list = items;
    }

    @Override
    public void addItem(T t) {
        list.add(t);
        notifyPropertyChanged(BR.items);
    }

    @Override
    public void updateItem(T t, int index) {
        list.set(index, t);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_places;
    }

    public abstract String getTitle();

    @Override
    public int getFilterTextChangedListenerId() {
        return BR.filterListener;
    }

}
