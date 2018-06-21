package com.edgespatial.zunguka.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.fragments.DirectionSuggestionFragment;
import com.mg.surblime.activities.ToolbarActivity;
import com.mg.surblime.util.Tools;

/**
 * Created by moses on 6/20/18.
 */

public class DirectionsActivity extends ToolbarActivity {

    private DirectionSuggestionFragment directionSuggestionFragment;
    private EditText startingPoint;
    private EditText destinationPoint;
    private EditText search;
    private ViewGroup editTextLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDisplayHomeAsUpEnabled(true);

        directionSuggestionFragment = (DirectionSuggestionFragment)
                getSupportFragmentManager().findFragmentById(R.id.directionsFragment);

        startingPoint = findViewById(R.id.startingPoint);
        destinationPoint = findViewById(R.id.destinationPoint);
        search = findViewById(R.id.search);
        editTextLayout = findViewById(R.id.editTextLayout);

        search.addTextChangedListener(directionSuggestionFragment);

        startingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.VISIBLE);
                TransitionManager.beginDelayedTransition(editTextLayout);
            }
        });
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
