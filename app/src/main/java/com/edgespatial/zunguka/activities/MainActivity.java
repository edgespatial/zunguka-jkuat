package com.edgespatial.zunguka.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.edgespatial.zunguka.R;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mg.surblime.activities.DrawerActivity;

public class MainActivity extends DrawerActivity implements OnMapReadyCallback, DrawerLayout.DrawerListener {


    private MapView mapView;
    private FloatingSearchView floatingSearchView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        floatingSearchView = findViewById(R.id.floating_search_view);
        floatingSearchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {
                openDrawer();
            }

            @Override
            public void onMenuClosed() {
                closeDrawer();
            }
        });

        getDrawer().addDrawerListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public int drawerId() {
        return R.id.drawer_layout;
    }

    @Override
    public int navigationViewId() {
        return R.id.nav_view;
    }

    @Override
    public int getDarkTheme() {
        return R.style.AppTheme_Dark_NoActionBar;
    }

    @Override
    public int getLightTheme() {
        return R.style.AppTheme_NoActionBar;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public int toolbarId() {
        return R.id.toolbar;
    }

    @Override
    public int getDarkThemeSwitchId() {
        return R.id.nav_dark_theme;
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {

    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {
        floatingSearchView.setLeftMenuOpen(false);
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
