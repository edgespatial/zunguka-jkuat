package com.edgespatial.zunguka.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.edgespatial.zunguka.R;
import com.edgespatial.zunguka.api.ZungukaAPI;
import com.edgespatial.zunguka.api.models.PhysicalLocation;
import com.edgespatial.zunguka.api.models.SearchHistory;
import com.edgespatial.zunguka.api.models.SearchResults;
import com.edgespatial.zunguka.fragments.BuildingsFragment;
import com.edgespatial.zunguka.fragments.GatesFragment;
import com.edgespatial.zunguka.fragments.SchoolsFragment;
import com.edgespatial.zunguka.fragments.WifiZonesFragment;
import com.edgespatial.zunguka.util.MapTools;
import com.edgespatial.zunguka.util.Settings;
import com.edgespatial.zunguka.util.ZungukaCache;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;
import com.mg.surblime.activities.DrawerActivity;
import com.mg.surblime.api.ModelDataListener;
import com.mg.surblime.util.Tools;

public class MainActivity extends DrawerActivity implements OnMapReadyCallback, DrawerLayout.DrawerListener {


    private static final int REQUEST_CODE_DIRECTIONS = 1001;
    private static final int REQUEST_CODE_PLACE = 1002;
    private static final String EXTRA_CURRENT_FRAGMENT = "current_fragment";
    private MapView mapView;
    private FloatingSearchView floatingSearchView;
    private MapTools mapTools;


    public static final String MAP_FRAGMENT = "map_fragment";
    public static final String BUILDINGS_FRAGMENT = "buildings_fragment";
    public static final String SCHOOLS_FRAGMENT = "schools_fragment";
    public static final String WIFI_ZONES_FRAGMENT = "wifi_zones";
    public static final String GATES_FRAGMENT = "gates_fragment";


    private static final String[] FRAGMENTS = new String[]{MAP_FRAGMENT, BUILDINGS_FRAGMENT, SCHOOLS_FRAGMENT, WIFI_ZONES_FRAGMENT, GATES_FRAGMENT};
    private String currentFragment;
    private int currentFragmentIndex = 0;
    private boolean reloadMap = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            this.reloadMap = true;
        }

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

        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                if (newQuery != null && !newQuery.isEmpty()) {
                    floatingSearchView.showProgress();
                    ZungukaAPI zungukaAPI = new ZungukaAPI(MainActivity.this);
                    zungukaAPI.setUseCache(true);
                    zungukaAPI.search(newQuery, new ModelDataListener<SearchResults>() {
                        @Override
                        public void onSuccess(SearchResults searchResults) {
                            floatingSearchView.swapSuggestions(searchResults.getResults());
                            floatingSearchView.hideProgress();
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                } else {
                    SearchHistory searchHistory = new ZungukaCache().with(MainActivity.this).get(SearchHistory.class);
                    if (searchHistory != null)
                        floatingSearchView.swapSuggestions(searchHistory.items);
                }
            }
        });

        floatingSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {
                PhysicalLocation location = (PhysicalLocation) item;
                leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), location.getIcon(), getTheme()));

                String text = location.getBody();
                String query = floatingSearchView.getQuery();
                int subString = text.toLowerCase().indexOf(query.toLowerCase());

                if (subString != -1) {
                    text = text.replaceFirst("(?i)" + floatingSearchView.getQuery(),
                            "<font color=\"" + Tools.getHex(getResources().getColor(R.color.colorAccent)) + "\">" + text.substring(subString, subString + query.length()) + "</font>");
                }
                textView.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
            }
        });

        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                mapTools.displaySingleLocation((PhysicalLocation) searchSuggestion);
                ZungukaCache zungukaCache = new ZungukaCache();
                zungukaCache.with(MainActivity.this);
                SearchHistory searchHistory = zungukaCache.get(SearchHistory.class);
                if (searchHistory == null) {
                    searchHistory = new SearchHistory();
                }
                searchHistory.add((PhysicalLocation) searchSuggestion);

                zungukaCache.save(searchHistory, SearchHistory.class);
                floatingSearchView.clearSearchFocus();
            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });

        floatingSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                SearchHistory searchHistory = new ZungukaCache().with(MainActivity.this).get(SearchHistory.class);
                if (searchHistory != null)
                    floatingSearchView.swapSuggestions(searchHistory.items);
            }

            @Override
            public void onFocusCleared() {

            }
        });
        switchFragments(savedInstanceState == null ? 0 : savedInstanceState.getInt(EXTRA_CURRENT_FRAGMENT, 0));
        getDrawer().addDrawerListener(this);
        initializeSatelliteSwitch();

        floatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_restore_map:
                        mapTools.initialize();
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (currentFragment.equals(MAP_FRAGMENT)) {
            if (floatingSearchView.isSearchBarFocused()) {
                floatingSearchView.clearSearchFocus();
            } else {
                super.onBackPressed();
            }
        } else {
            if (getDrawer().isDrawerOpen(GravityCompat.START)) {
                closeDrawer();
            } else {
                switchFragments(0);
            }
        }
    }

    public void showPositionMarker(final PhysicalLocation physicalLocation) {
        switchFragments(0);
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mapTools.displaySingleLocation(physicalLocation);
                    }
                }, 1000);
    }

    public MapTools getMapTools() {
        return mapTools;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_CURRENT_FRAGMENT, currentFragmentIndex);
        super.onSaveInstanceState(outState);
    }

    public SupportMapFragment getSupportMapFragment() {
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentByTag(MAP_FRAGMENT);
        return supportMapFragment;
    }

    private void initializeSatelliteSwitch() {
        MenuItem darkThemeSwitch = ((NavigationView) findViewById(navigationViewId())).getMenu().findItem(R.id.nav_satellite);
        SwitchCompat switchCompat = (SwitchCompat) darkThemeSwitch.getActionView();
        switchCompat.setChecked(Settings.isSatelliteMapStyle(this));
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.setSatelliteMapStyle(MainActivity.this, isChecked);
                mapTools.updateMapStyle();
                closeDrawer();
            }
        });
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(currentFragment);
    }

    public void switchFragments(Fragment fragment, FragmentTransaction fragmentTransaction, String tag) {
        Fragment previousFragment = getSupportFragmentManager().findFragmentByTag(this.currentFragment);
        Fragment newFragment = getSupportFragmentManager().findFragmentByTag(tag);

        if (tag.equals(MAP_FRAGMENT) && reloadMap) {
            fragmentTransaction.remove(newFragment);
            newFragment = null;
            reloadMap = false;
        }

        if (newFragment == null) {
            newFragment = fragment;
            fragmentTransaction.add(R.id.mainContent, newFragment, tag);
            newFragment.setEnterTransition(new Fade());
            fragmentTransaction.commit();
        } else {
            newFragment.setEnterTransition(new Fade());
            fragmentTransaction.show(getSupportFragmentManager().findFragmentByTag(tag));
            fragmentTransaction.commit();
        }

        if (previousFragment != null && !currentFragment.equals(tag)) {
            previousFragment.setExitTransition(new Fade());
            getSupportFragmentManager().beginTransaction().hide(previousFragment).commit();
        }
    }

    public int getMainContentView() {
        return R.id.mainContent;
    }

    public void switchFragments(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switchFragments(fragment, fragmentTransaction, tag);
    }

    public void switchFragments(int index) {
        NavigationView navigationView = findViewById(navigationViewId());
        int currentSelectedItem = 0;
        Fragment newFragment = null;
        floatingSearchView.setVisibility(index == 0 ? View.VISIBLE : View.GONE);

        switch (index) {
            case 0:
                newFragment = new SupportMapFragment();
                ((SupportMapFragment) newFragment).getMapAsync(this);
                currentSelectedItem = R.id.nav_home;
                break;
            case 1:
                newFragment = new BuildingsFragment();
                currentSelectedItem = R.id.nav_buildings;
                setTitle(R.string.buildings);
                break;
            case 2:
                newFragment = new SchoolsFragment();
                currentSelectedItem = R.id.nav_schools;
                setTitle(R.string.schools);
                break;
            case 3:
                newFragment = new WifiZonesFragment();
                currentSelectedItem = R.id.nav_wifi_zones;
                setTitle(R.string.wifi_zones);
                break;
            case 4:
                newFragment = new GatesFragment();
                currentSelectedItem = R.id.nav_gates;
                setTitle(R.string.gates);
                break;
        }
        if (newFragment != null) {
            newFragment.setRetainInstance(true);
            navigationView.setCheckedItem(currentSelectedItem);
            Tools.hideKeyboard(this);
        }
        switchFragments(newFragment, FRAGMENTS[index]);
        currentFragment = FRAGMENTS[index];
        currentFragmentIndex = index;
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationView navigationView = findViewById(navigationViewId());
        if (navigationView.getMenu().findItem(R.id.nav_directions).isChecked()) {
            switchFragments(currentFragmentIndex);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_directions:
                showDirections();
                break;
            case R.id.nav_home:
                switchFragments(0);
                break;
            case R.id.nav_buildings:
                switchFragments(1);
                break;
            case R.id.nav_schools:
                switchFragments(2);
                break;
            case R.id.nav_wifi_zones:
                switchFragments(3);
                break;
            case R.id.nav_gates:
                switchFragments(4);
                break;
        }
        closeDrawer();
        return true;
    }

    private void showDirections() {
        Intent intent = new Intent(this, DirectionsActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DIRECTIONS);
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
        mapTools = new MapTools(mapboxMap, this, (MapView) getSupportMapFragment().getView());
        mapTools.initialize();
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
