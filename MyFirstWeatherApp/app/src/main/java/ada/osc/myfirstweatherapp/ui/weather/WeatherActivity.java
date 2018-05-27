package ada.osc.myfirstweatherapp.ui.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import ada.osc.myfirstweatherapp.Constants;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.model.LocationWrapper;
import ada.osc.myfirstweatherapp.ui.CustomViewPagerFragmentAdapter;
import ada.osc.myfirstweatherapp.ui.addLocation.AddNewLocationActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.main_activity_drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.main_activity_navigation_view) NavigationView mNavigationView;
    @BindView(R.id.main_activity_view_pager) ViewPager viewPager;


    /*private Toolbar mToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView;
    private ViewPager viewPager;*/
    private CustomViewPagerFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewPager();
        initToolbar();

        adapter = new CustomViewPagerFragmentAdapter(getSupportFragmentManager());

        ArrayList<LocationWrapper> locationWrappers = new ArrayList<>();
        locationWrappers.add(new LocationWrapper("Osijek"));

        adapter.setAdapterData(locationWrappers);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initNavigationDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewPager() {
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void initNavigationDrawer() {
        //mNavigationView = (NavigationView) findViewById(R.id.main_activity_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleItemSelectedClick(item.getItemId());
                return false;
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.main_activity_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void handleItemSelectedClick(int itemID) {
        switch (itemID) {
            case R.id.menu_add_new_location: {
                startActivity(new Intent(this, AddNewLocationActivity.class));
                drawerLayout.closeDrawers();
                break;
            }
        }
    }

    private void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    setWeatherIcon(Constants.SNOW);
                    break;
                }
                case Constants.RAIN_CASE: {
                    setWeatherIcon(Constants.RAIN);
                    break;
                }
                case Constants.CLEAR_CASE: {
                    setWeatherIcon(Constants.SUN);
                    break;
                }
                case Constants.MIST_CASE: {
                    setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.FOG_CASE: {
                    setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.HAZE_CASE: {
                    setWeatherIcon(Constants.FOG);
                    break;
                }

                case Constants.CLOUD_CASE: {
                    setWeatherIcon(Constants.CLOUD);
                    break;
                }
            }
    }

    // TODO: 18/05/2018 load image using the constants (hint image base + path)
    private void setWeatherIcon(String iconPath) {

    }

    private double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }
}