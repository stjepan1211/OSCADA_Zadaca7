package ada.osc.myfirstweatherapp.presentation.weather;

import ada.osc.myfirstweatherapp.ui.weather.WeatherContract;

public class WeatherActivityPresenter implements WeatherContract.Presenter {

    private WeatherContract.View mWeatherView;


    @Override
    public void setView(WeatherContract.View weatherView) {
        this.mWeatherView = weatherView;
    }
}

