package ada.osc.myfirstweatherapp.presentation.weather;

import ada.osc.myfirstweatherapp.Constants;
import ada.osc.myfirstweatherapp.interactor.ApiInteractor;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.ui.weather.fragments.WeatherFragmentContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherFragmentPresenter implements WeatherFragmentContract.Presenter {

    private final ApiInteractor mApiInteractor;

    private WeatherFragmentContract.View mWeatherView;

    public WeatherFragmentPresenter(ApiInteractor apiInteractor) {
        this.mApiInteractor = apiInteractor;
    }


    @Override
    public void setView(WeatherFragmentContract.View weatherView) {
        this.mWeatherView = weatherView;
    }

    @Override
    public void getWeather(String appId, String cityToDisplay) {
        mApiInteractor.getWeather(getWeatherCallback(), Constants.APP_ID, cityToDisplay);
    }

    private Callback<WeatherResponse> getWeatherCallback() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse data = response.body();

                    mWeatherView.showWeather(data);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // TODO: 19/05/2018 add error handling
            }
        };
    }
}
