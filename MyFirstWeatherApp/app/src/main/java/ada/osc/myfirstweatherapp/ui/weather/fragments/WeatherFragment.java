package ada.osc.myfirstweatherapp.ui.weather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ada.osc.myfirstweatherapp.App;
import ada.osc.myfirstweatherapp.Constants;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.presentation.weather.WeatherFragmentPresenter;
import ada.osc.myfirstweatherapp.util.NetworkUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragment extends Fragment implements WeatherFragmentContract.View{

    @BindView(R.id.weather_display_current_temperature_text_view) TextView mCurrentTemperature;
    @BindView(R.id.weather_fragment_min_temperature_text_view) TextView mMinTemperature;
    @BindView(R.id.weather_fragment_max_temperature_text_view) TextView mMaxTemperature;
    @BindView(R.id.weather_display_pressure_text_view) TextView mPressure;
    @BindView(R.id.weather_display_wind_text_view) TextView mWind;
    @BindView(R.id.weather_display_detailed_description_text_view) TextView mDescription;
    @BindView(R.id.weather_display_weather_icon_image_view) ImageView mWeatherIcon;

    private WeatherFragmentContract.Presenter mPresenter;


    public static WeatherFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        WeatherFragment f = new WeatherFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new WeatherFragmentPresenter(App.getApiInteractor());
        mPresenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        String cityToDisplay = getArguments().getString(Constants.CITY_BUNDLE_KEY);

        mPresenter.getWeather(Constants.APP_ID, cityToDisplay);
    }


    @Override
    public void showWeather(WeatherResponse data) {
        setCurrentTemperatureValues(data.getMain().getTemp_max());
        setPressureValues(data.getMain().getPressure());
        setDescriptionValues(data.getWeatherObject().getDescription());
    }

    @Override
    public void setCurrentTemperatureValues(double temperatureValues) {
        mCurrentTemperature.setText(getString(R.string.current_temperature_message, temperatureValues));
    }

    @Override
    public void setMinTemperatureValues(double minTemperatureValues) {
        mMinTemperature.setText(getString(R.string.minimum_temperature_message, minTemperatureValues));
    }

    @Override
    public void setMaxTemperatureValues(double maxTemperatureValues) {
        mMaxTemperature.setText(getString(R.string.maximum_temperature_message, maxTemperatureValues));
    }

    @Override
    public void setPressureValues(double pressureValues) {
        mPressure.setText(getString(R.string.pressure_message, pressureValues));
    }

    @Override
    public void setWindValues(double windValues) {
        mWind.setText(getString(R.string.wind_speed_message, windValues));
    }

    @Override
    public void setWeatherIcon(String iconPath) {
        Glide.with(getActivity().getApplicationContext()).load(Constants.IMAGE_BASE_URL + iconPath).into(mWeatherIcon);
    }

    @Override
    public void setDescriptionValues(String descriptionValues) {
        mDescription.setText(descriptionValues);
    }

    @Override
    public void refreshCurrentData() {

    }

    @Override
    public void toastMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkIsConnected() {
        if(!NetworkUtils.checkIfInternetConnectionIsAvailable(getActivity().getApplicationContext())){
            toastMessage("Not connected");
        }
    }

}
