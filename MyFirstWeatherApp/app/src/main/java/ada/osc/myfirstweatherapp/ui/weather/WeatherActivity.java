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
import ada.osc.myfirstweatherapp.presentation.weather.WeatherActivityPresenter;
import ada.osc.myfirstweatherapp.ui.CustomViewPagerFragmentAdapter;
import ada.osc.myfirstweatherapp.ui.addLocation.AddLocationActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View{

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.main_activity_drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.main_activity_navigation_view) NavigationView mNavigationView;
    @BindView(R.id.main_activity_view_pager) ViewPager mViewPager;

    private CustomViewPagerFragmentAdapter mAdapter;
    private WeatherContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewPager();
        initToolbar();

        mPresenter = new WeatherActivityPresenter();
        mPresenter.setView(this);

        mAdapter = new CustomViewPagerFragmentAdapter(getSupportFragmentManager());

        ArrayList<LocationWrapper> locationWrappers = new ArrayList<>();
        locationWrappers.add(new LocationWrapper("Osijek"));

        mAdapter.setAdapterData(locationWrappers);
        mViewPager.setAdapter(mAdapter);
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
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void createWeatherIconValue(String description) {
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

    @Override
    public void handleItemSelectedClick(int itemId) {
        switch (itemId) {
            case R.id.menu_add_new_location: {
                startActivity(new Intent(this, AddLocationActivity.class));
                mDrawerLayout.closeDrawers();
                break;
            }
        }
    }

    @Override
    public void setWeatherIcon(String iconPath) {

    }

    @Override
    public double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.main_activity_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void initNavigationDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleItemSelectedClick(item.getItemId());
                return false;
            }
        });
    }

    @Override
    public void initViewPager() {
        if (mViewPager != null) {
            mViewPager.setOffscreenPageLimit(3);
        }
    }
}