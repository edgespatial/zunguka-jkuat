package com.edgespatial.zunguka.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.api.models.PhysicalLocation;
import com.edgespatial.zunguka.api.models.SearchHistory;
import com.edgespatial.zunguka.fragments.DirectionSuggestionFragment;
import com.edgespatial.zunguka.util.ZungukaCache;
import com.github.jorgecastilloprz.FABProgressCircle;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.directions.v5.models.RouteLeg;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mg.surblime.ObservableRecyclerViewModel;
import com.mg.surblime.activities.ToolbarActivity;
import com.mg.surblime.util.Tools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by moses on 6/20/18.
 */

public class DirectionsActivity extends ToolbarActivity implements ObservableRecyclerViewModel.OnItemClickListener<PhysicalLocation> {

    private DirectionSuggestionFragment directionSuggestionFragment;
    private RadioButton startingPoint;
    private RadioButton destinationPoint;
    private ImageView switchLocations;
    private EditText search;
    private ViewGroup editTextLayout;
    private RadioGroup radioGroup;
    private FloatingActionButton floatingActionButton;
    private FABProgressCircle fabProgressCircle;
    private ViewGroup mainLayout;
    private ViewGroup progressBar;

    private PhysicalLocation start, destination;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.directions));

        directionSuggestionFragment = (DirectionSuggestionFragment)
                getSupportFragmentManager().findFragmentById(R.id.directionsFragment);

        startingPoint = findViewById(R.id.startingPoint);
        destinationPoint = findViewById(R.id.destinationPoint);
        search = findViewById(R.id.search);
        editTextLayout = findViewById(R.id.editTextLayout);
        switchLocations = findViewById(R.id.switchLocations);
        radioGroup = findViewById(R.id.radioGroup);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        mainLayout = findViewById(R.id.mainLayout);
        fabProgressCircle = findViewById(R.id.fabProgressCircle);
        progressBar = findViewById(R.id.progress);

        progressBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        search.addTextChangedListener(directionSuggestionFragment);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.VISIBLE);
                search.requestFocus();
                TransitionManager.beginDelayedTransition(editTextLayout);
            }
        };
        startingPoint.setOnClickListener(onClickListener);
        destinationPoint.setOnClickListener(onClickListener);

        switchLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (destination != null && start != null) {
                    PhysicalLocation temp = start;
                    start = destination;
                    destination = temp;

                    startingPoint.setText(start.name);
                    destinationPoint.setText(destination.name);
                    TransitionManager.beginDelayedTransition(radioGroup);

                }
            }
        });

        startingPoint.performClick();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                ZungukaCache zungukaCache = new ZungukaCache();
                zungukaCache.with(DirectionsActivity.this);
                SearchHistory searchHistory = zungukaCache.get(SearchHistory.class);

                if (searchHistory == null) {
                    searchHistory = new SearchHistory();
                }
                searchHistory.add(start);
                searchHistory.add(destination);

                zungukaCache.save(searchHistory, SearchHistory.class);
                fabProgressCircle.show();
                NavigationRoute.builder(DirectionsActivity.this)
                        .accessToken(Mapbox.getAccessToken())
                        .voiceUnits(DirectionsCriteria.METRIC)
                        .origin(Point.fromLngLat(start.getLatlng().getLongitude(), start.getLatlng().getLatitude()))
                        .destination(Point.fromLngLat(destination.getLatlng().getLongitude(), destination.getLatlng().getLatitude()))
                        .build()
                        .getRoute(new Callback<DirectionsResponse>() {
                            @Override
                            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                                fabProgressCircle.hide();
                                progressBar.setVisibility(View.GONE);
                                DirectionsResponse directionsResponse = response.body();
                                if (directionsResponse != null) {
                                    List<DirectionsRoute> routes = directionsResponse.routes();
                                    if (!routes.isEmpty()) {
                                        NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                                                .directionsRoute(routes.get(0))
                                                .shouldSimulateRoute(true)
                                                .build();

                                        NavigationLauncher.startNavigation(DirectionsActivity.this, options);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public void onItemClick(View view, PhysicalLocation physicalLocation) {
        RadioButton current = startingPoint.isChecked() ? startingPoint : destinationPoint;
        Tools.hideKeyboard(current);
        search.setVisibility(View.GONE);
        search.requestFocus();
        TransitionManager.beginDelayedTransition(editTextLayout);

        current.setText(physicalLocation.name);
        search.setText("");

        if (startingPoint.isChecked()) {
            start = physicalLocation;
            destinationPoint.performClick();
        } else {
            destination = physicalLocation;
        }

        if (start != null && destination != null && !start.isEqual(destination)) {
            fabProgressCircle.setVisibility(View.VISIBLE);
        } else {
            fabProgressCircle.setVisibility(View.GONE);
        }
        TransitionManager.beginDelayedTransition(mainLayout);
    }

    @Override
    public boolean onItemLongClick(View view, PhysicalLocation physicalLocation) {
        return false;
    }
}
