package com.edgespatial.zunguka.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.edgespatial.zunguka.R;
import com.mg.surblime.activities.ToolbarActivity;
import com.mg.surblime.util.Tools;

/**
 * Created by moses on 6/20/18.
 */

public class DirectionsActivity extends ToolbarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_directions);
    }

    @Override
    public int toolbarId() {
        return R.id.toolbar;
    }

    @Override
    public int getStyleTheme() {
        return Tools.isDarkTheme(this) ? R.style.AppTheme_Dark_NoActionBar : R.style.AppTheme_NoActionBar;
    }
}
