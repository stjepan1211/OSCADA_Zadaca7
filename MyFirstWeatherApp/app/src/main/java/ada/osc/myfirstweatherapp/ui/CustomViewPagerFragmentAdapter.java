package ada.osc.myfirstweatherapp.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import ada.osc.myfirstweatherapp.ui.weather.fragments.WeatherFragment;
import ada.osc.myfirstweatherapp.model.LocationWrapper;

public class CustomViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private final ArrayList<LocationWrapper> mCitiesList = new ArrayList<>();

    public CustomViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(mCitiesList.get(position).getLocation());
    }

    @Override
    public int getCount() {
        return mCitiesList.size();
    }

    public void setAdapterData(ArrayList<LocationWrapper> dataSource) {
        this.mCitiesList.clear();
        this.mCitiesList.addAll(dataSource);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCitiesList.get(position).getLocation();
    }
}